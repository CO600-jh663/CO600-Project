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

    //TODO: Dates?... GET FROM CALENDAR-ACTIVITY!
    private String fromDate="";
    private String toDate="";

    //for LL-Parser
    private String[] production;
    private HashMap<String, Integer> table;
    private Stack<String> stack;


    //TODO: have parser run in different thread??
    public FeedParser(FeedStore store) {
        output = new ArrayList<ArrayList<String>>();
        this.store = store;

        /**
         * --\/-for LL-Parser
         */
        production = new String[11];
        //initialise---EOF-\/
        production[0] = "[E]$";
        production[1] = "{F}E'";
        production[2] = ",E";
        production[3] = "e";
        production[4] = "\"C\":VF'";
        production[5] = ",F";
        production[6] = "e";
        production[7] = "\"C\"";
        production[8] = "N";
        production[9] = "string";
        production[10] = "number";
        //TODO: Add state to lex tokens of length 2.
        //TODO: Add states to lex 'empty' and 'value'.

        table = new HashMap<String, Integer>();
        //X:NON-TERMINAL Y:TERMINAL
        table.put("S[", 0);
        table.put("E']", 3);
        table.put("E{", 1);
        table.put("F'}", 6);
        table.put("F',", 5);
        table.put("E',", 2);
        table.put("F\"", 4);
        table.put("V\"", 7);
        table.put("Cvalue", 9);
        table.put("Vnumber", 8);
        table.put("Nnumber", 10);

        stack = new Stack<String>();
        //Initialise
        stack.push("S");
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
                address = (events1+fromDate+events2+toDate);
                break;
            //Will throw exception!
            default: address = "";
        }
        try {
            file = Jsoup.connect(address).timeout(10000).ignoreContentType(true).get();
            //OUTPUT
            feed = file.getElementsByTag("body").text();
            //Automatically Disconnects...

            stream = new StringReader(feed);
        } catch (Exception e) {Log.e("No Internet", e.getMessage());}

        //BEGIN PARSING THEN OUTPUT
        try {
            return parse();
        } catch (FeedParseException e){Log.e("", e.getMessage());}
        //de-fault
        return null;
    }


    /**
     * FOR LOCAL-DATA
     */
    public ArrayList<ArrayList<String>> parseThis(String dat)
    {
        //overrides stream
        stream = new StringReader(dat);
        //parse dat
        try {
            return parse();
        }
        catch (FeedParseException e){Log.e("", e.getMessage());}
        //de-fault :(
        return null;
    }

    //De-fault method
    private ArrayList<ArrayList<String>> parse() throws FeedParseException
    {
        /**
         * Resource is opened on startup/getFeed().
         */
        movePointer();

        //RECURSIVE DESCENT or LL??
        //parseS();
        llParse();

        //close resource
        close();

        //output
        return output();
    }


    /**
     * RECURSIVE DESCENT
     */
    private void parseS() throws FeedParseException
    {
        switch (pointer) {
            case "[":
                movePointer();
                parseE();


                //------\/-ASSUMES pointer AT END-OF-FILE!!
                if (!(pointer.equals("]"))){
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
    private void parseE() throws FeedParseException
    {
        ArrayList<String> element = new ArrayList<String>();
        switch (pointer){
            case "{":
                movePointer();
                element = parseF(element);
                if (!(pointer.equals("}"))){
                    throw new FeedParseException("at E");
                }
                else{
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
    private void parseE2() throws FeedParseException
    {
        switch (pointer){
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
    private ArrayList<String> parseF(ArrayList<String> element) throws FeedParseException
    {
        String title = "";
        String value = "";

        switch (pointer){
            case "\"":
                movePointer();
                title = parseC();
                //assumes just-after end of string
                if (!(pointer.equals("\""))){
                    throw new FeedParseException("at F");
                }
                else{
                    movePointer();
                    if (!(pointer.equals(":"))){
                        throw new FeedParseException("at F");
                    }
                    else{
                        movePointer();
                        value = parseV(value);
                    }
                }
                //Needed for output-format
                element.add(title+":"+value);
                /**
                 * RETURNS: E() or F2()
                 */
                return parseF2(element);
            default:
                throw new FeedParseException("Element empty!");
        }
    }
    private ArrayList<String> parseF2(ArrayList<String> element) throws FeedParseException
    {
        switch (pointer){
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
    private String parseV(String value) throws FeedParseException
    {
        //V -> " C " | N.
        switch (pointer){
            case "\"":
                movePointer();
                value = (value+parseC());
                if (!(pointer.equals("\""))){
                    throw new FeedParseException("at V");
                }
                else {
                    //else return
                    movePointer();
                    return value;
                }
            default:
                return (value+parseN());
        }
    }
    private String parseN()
    {
        /**
         *   \/-NUMBER-RegAut.
         *   TERMINATORS:
         *      ',' <- end-of-field
         *      '}' <- end-of-element
         */
        String num = "";
        while (!(pointer.equals(",") || pointer.equals("}"))){
            //No special states' needed.
            num += pointer;

            movePointer();
        }
        //Don't move pointer!
        return num;
    }
    private String parseC()
    {
        /**
         *   \/-STRING-RegAut.
         *   TERMINATORS:
         *      '"' <- end-of-string
         */
        String str = "";
        while (!(pointer.equals("\""))){
            //-it means '\'-----\/
            if (pointer.equals("\\")){
                movePointer();
                if (pointer.equals("\"")){
                    str = (str+"\"");
                }
                else{
                    //for html such as: \n
                    str = (str+"\\"+pointer);

                    movePointer();
                }
            }
            //Normal state
            else{
                str = (str+pointer);

                movePointer();
            }
        }
        //DO NOT MOVE-POINTER!!... terminal belongs to: F or V
        return str;
    }


    private void llParse() throws FeedParseException
    {
        /**
         * LL-Parser with "data-extraction points"!
         */
        //@DATA-extraction: define
        ArrayList<String> elmnt = new ArrayList<String>();
        String fld = "";

        ArrayList<String> expansion;
        //TODO: How to get fields and elements?!?!?!?!?
        while (!(stack.peek().equals("$"))){

            //@DATA
            if (stack.peek().equals("number") || stack.peek().equals("string")){
                //@DATA-extraction: make field
                if (fld.equals("")){
                    //parse title
                    if (stack.peek().equals("number")) {
                        //title can't be a number!!
                        throw new FeedParseException("llParser: number can't be title!");
                    }
                    else {
                        //get string
                        fld = parseC();
                    }
                }
                else{
                    //parse value
                    if (stack.peek().equals("number")) {
                        fld = (fld+":"+parseN());
                    }
                    else {
                        //get string
                        fld = (fld+":"+parseC());
                    }
                    //output field
                    elmnt.add(fld);
                    //refresh fld
                    fld = "";
                }

            }

            //Normal case
            else{
                if (stack.peek().equals(pointer)){

                    //@DATA-extraction: make element
                    switch (pointer){
                        case "{":
                            //new element!
                            elmnt = new ArrayList<String>();
                            break;
                        case "}":
                            //end-of-element thus output
                            output.add(elmnt);
                    }

                    //Ignored
                    stack.pop();
                    movePointer();
                }
                else {
                    if (table.containsKey(stack.peek() + pointer)){
                        expansion = lex(production[table.get(stack.peek() + pointer)]);
                        //replaces nonTerminal for its' production
                        stack.pop();
                        for (String ch : expansion){
                            stack.push(ch);
                        }
                        //Don't move-pointer if mismatch!!
                    }
                    else {
                        if (stack.peek().equals("e")){
                            //removes empty
                            stack.pop();

                        }
                        else{
                            //I assume this encapsulates every-possible problem
                            throw new FeedParseException("Problem with LL-parsing!!");
                        }
                    }
                }
            }
        }
    }
    private ArrayList<String> lex(String expression)
    {
        ArrayList<String> list = new ArrayList<String>();
        //@TEST
        String point;
        for (int y = 0; (y < (expression.length())); y++){

           point = expression.substring(y, (y+1));

            switch (point){
                //string-case
                case "s":
                    list.add("string");
                    //next char
                    y = (y+5);
                    break;
                //number-case
                case "n":
                    list.add("number");
                    //next char
                    y = (y+5);
                    break;
                //Is it E'?
                case "E":
                    if (expression.substring(y, (y+2)).equals("E'")){
                        list.add("E'");
                        //next char
                        y++;
                    }
                    else{
                        list.add("E");
                    }
                    break;
                //Is it F'?
                case "F":
                    if (expression.substring(y, (y+2)).equals("F'")){
                        list.add("F'");
                        //next char
                        y++;
                    }
                    else{
                        list.add("F");
                    }
                    break;
                default:
                    //ALL OTHER SYMBOLS ARE OF LENGTH: 1!!
                    list.add(expression.substring(y, (y+1)));
            }
        }
        list = inverse(list);
        //output
        return list;
    }

    private ArrayList<String> inverse(ArrayList<String> list)
    {
        ArrayList<String> inverted = new ArrayList<String>();
        Object[] temp;
        temp = list.toArray();
        for (int i = (temp.length - 1); (i >= 0); i--){
            inverted.add((String)temp[i]);
        }
        return inverted;
    }


    /**
     * MOVES TO NEXT LETTER
     */
    private void movePointer()
    {
        try {
            pointer = Character.toString((char)stream.read());
        } catch (IOException e){Log.e("", "POINTER NOT WORKING!!");}
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
    private ArrayList<ArrayList<String>> output()
    {
        //TODO: Later... format html in body tag!!

        //output
        return output;
    }

    public void refresh()
    {
        output = new ArrayList<ArrayList<String>>();
    }
}