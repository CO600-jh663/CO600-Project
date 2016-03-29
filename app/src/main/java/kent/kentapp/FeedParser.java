package kent.kentapp;

import java.util.*;
import java.io.*;

import android.util.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class FeedParser {
    //news-URL
    private final String news = "http://www.kentunion.co.uk/svc/feeds/news/6001?subtree=false&imagesize=medium-square";
    private final String events1 = "http://www.kentunion.co.uk/svc/feeds/events/6001?subtree=true&imagesize=medium-square&from=%5b";
    private final String events2 = "%5d&to=%5b";

    //For context purposes only...
    FeedStore store;

    //INPUT
    private StringReader stream;

    //FOR OUTPUT
    private ArrayList<ArrayList<String>> output;

    //pointer for both parsers
    private String pointer;

    //TODO: dates not necessary :/
    private String fromDate = "";
    private String toDate = "";

    //for LL-Parser
    private String[] production;
    private HashMap<String, Integer> table;
    private Stack<String> stack;


    //TODO: have parser run in different thread??
    public FeedParser(FeedStore store)
    {
        output = new ArrayList<ArrayList<String>>();
        this.store = store;
    }


    /**
     * PRIMARY-METHOD
     */
    public ArrayList<ArrayList<String>> getFeed(String type) {
        String feed;
        String address;

        //client
        Document file;

        /**
         * FEED URLs-------\/
         */
        switch (type) {
            case "news":
                address = news;
                break;
            case "events":
                address = (events1 + fromDate + events2 + toDate);
                break;
            //Will throw exception!
            default:
                address = "";
        }
        try {
            file = Jsoup.connect(address).timeout(10000).ignoreContentType(true).get();
            //OUTPUT
            feed = file.getElementsByTag("body").text();
            //Automatically Disconnects...

            stream = new StringReader(feed);
        } catch (Exception e) {
            Log.e("No Internet", e.getMessage());
        }

        //BEGIN PARSING THEN OUTPUT
        try {
            return parse();
        } catch (FeedParseException e) {
            Log.e("", e.getMessage());
        }
        //de-fault
        return null;
    }


    /**
     * FOR LOCAL-DATA
     */
    public ArrayList<ArrayList<String>> parseThis(String dat) {
        //overrides stream
        stream = new StringReader(dat);
        //parse dat
        try {
            return parse();
        } catch (FeedParseException e) {
            Log.e("", e.getMessage());
        }
        //de-fault :(
        return null;
    }

    //De-fault method
    private ArrayList<ArrayList<String>> parse() throws FeedParseException {
        /**
         * Resource is opened on startup/getFeed().
         */
        movePointer();

        //RECURSIVE DESCENT
        parseS();

        //close resource
        close();

        //output
        return output();
    }


    /**
     * RECURSIVE DESCENT
     */
    private void parseS() throws FeedParseException {
        switch (pointer) {
            case "[":
                movePointer();
                parseE();


                //------\/-ASSUMES pointer AT END-OF-FILE!!
                if (!(pointer.equals("]"))) {
                    throw new FeedParseException("at S");
                }
                /**
                 * RETURNS: parse()
                 */
                break;
            default:
                throw new FeedParseException("at S");
        }
    }

    private void parseE() throws FeedParseException {
        ArrayList<String> element = new ArrayList<String>();
        switch (pointer) {
            case "{":
                movePointer();
                element = parseF(element);
                if (!(pointer.equals("}"))) {
                    throw new FeedParseException("at E");
                } else {
                    //store element 1st!
                    output.add(element);
                    movePointer();
                    parseE2();
                    /**
                     * RETURNS: S() or E2()
                     */
                }
                break;
            default:
                throw new FeedParseException("Feed-file empty!");
        }
    }

    private void parseE2() throws FeedParseException {
        switch (pointer) {
            case ",":
                movePointer();
                parseE();
                break;
            //EMPTY-CASE
            case "]":
                //DON'T MOVE!
                //Terminal belongs to E
                /**
                 * RETURNS: E()
                 */
                break;
            default:
                throw new FeedParseException("at E2");
        }
    }

    private ArrayList<String> parseF(ArrayList<String> element) throws FeedParseException {
        String title = "";
        String value = "";

        switch (pointer) {
            case "\"":
                movePointer();
                title = parseC();
                //assumes just-after end of string
                if (!(pointer.equals("\""))) {
                    throw new FeedParseException("at F");
                } else {
                    movePointer();
                    if (!(pointer.equals(":"))) {
                        throw new FeedParseException("at F");
                    } else {
                        movePointer();
                        value = parseV(value);
                    }
                }
                //Needed for output-format
                element.add(title + ":" + value);
                /**
                 * RETURNS: E() or F2()
                 */
                return parseF2(element);
            default:
                throw new FeedParseException("Element empty!");
        }
    }

    private ArrayList<String> parseF2(ArrayList<String> element) throws FeedParseException {
        switch (pointer) {
            case ",":
                movePointer();
                /**
                 * RETURNS: E() or F()
                 */
                return parseF(element);
            //EMPTY-CASE
            case "}":
                //Don't move-pointer!
                return element;
            default:
                throw new FeedParseException("at F2");
        }
    }

    private String parseV(String value) throws FeedParseException {
        //V -> " C " | N.
        switch (pointer) {
            case "\"":
                movePointer();
                value = (value + parseC());
                if (!(pointer.equals("\""))) {
                    throw new FeedParseException("at V");
                } else {
                    //else return
                    movePointer();
                    return value;
                }
            default:
                return (value + parseN());
        }
    }

    private String parseN() {
        /**
         *   \/-NUMBER-RegAut.
         *   TERMINATORS:
         *      ',' <- end-of-field
         *      '}' <- end-of-element
         */
        String num = "";
        while (!(pointer.equals(",") || pointer.equals("}"))) {
            //No special states' needed.
            num += pointer;

            movePointer();
        }
        //Don't move pointer!
        return num;
    }

    private String parseC() {
        /**
         *   \/-STRING-RegAut.
         *   TERMINATORS:
         *      '"' <- end-of-string
         */
        String str = "";
        while (!(pointer.equals("\""))) {
            //-it means '\'-----\/
            if (pointer.equals("\\")) {
                movePointer();
                if (pointer.equals("\"")) {
                    str = (str + "\"");

                    movePointer();
                }
                else {
                    //for html such as: \n
                    str = (str + "\\" + pointer);

                    movePointer();
                }
            }
            //Normal state
            else {
                str = (str + pointer);

                movePointer();
            }
        }
        //DO NOT MOVE-POINTER!!... terminal belongs to: F or V
        return str;
    }


    /**
     * MOVES TO NEXT LETTER
     */
    private void movePointer() {
        try {
            pointer = Character.toString((char) stream.read());
        } catch (IOException e) {
            Log.e("", "POINTER NOT WORKING!!");
        }
    }

    /**
     * CLOSES RESOURCE
     */
    private void close() {
        stream.close();
    }

    /**
     * OUTPUT
     */
    private ArrayList<ArrayList<String>> output() {
        //TODO: Later... format html in body tag!!

        //output
        return output;
    }

    public void refresh() {
        output = new ArrayList<ArrayList<String>>();
    }
}