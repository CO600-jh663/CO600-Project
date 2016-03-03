package kent.kentapp;

import java.net.*;
import java.util.*;
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class FeedParser
{
    //INPUT
    private StringReader stream;

    //FOR OUTPUT
    private ArrayList<ArrayList<String>> output;

    //\/--INSTANTIATED PARSER!!
    FeedStore store;

    //pointer for parser
    private String pointer;


    public FeedParser(FeedStore store)
    {
        output = new ArrayList<ArrayList<String>>();
        this.store = store;
    }


    //PRIMARY METHOD\\//
    public ArrayList<ArrayList<String>> getFeed(String type)
    {
        String feed = "";
        String address = "";

        /**
         * FEED URLs-------\/
         */
        switch (type){
            case "news":
                //refer to Email-Dragos for GET API
                address = "https://www.kentunion.co.uk/svc/feeds/news/6001?subtree=false&imagesize=medium-square";
                break;
            case "events":
                address = "http://www.kentunion.co.uk/svc/feeds/events/6001?subtree=true&imagesize=medium-square&from=%5bfromDate%5d&to=%5btoDate";
        }
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(address)).openConnection();
            connection.setRequestMethod("GET");
            //TODO: @Test
            //-------\/--THIS ONE WILL CAUSE ERRORS!!
            feed = (String)connection.getContent();
            connection.disconnect();

            //OUTPUT
            stream = new StringReader(feed);
        }
        catch(Exception e){}

        //BEGIN PARSING\\//--THEN OUTPUT//\\
        try {
            return parse();
        }
        catch(FeedParseException e){}
        //de-fault
        return null;
    }

    private ArrayList<ArrayList<String>> parse() throws FeedParseException
    {
        /**
         * Resource is opened on startup/getFeed().
         */

        //RECURSIVE DESCENT
        //TODO: @Test: see that this works PERFECTLY(no exceptions)!!
        parseS();

        //close resource
        close();

        //output
        return output();
    }


    /**
     * PARSING METHODS HERE
     */
    private void parseS() throws FeedParseException
    {

        switch (pointer) {
            case "[":
                movePointer();
                parseE();
                break;
            default:
                throw new FeedParseException();
        }
    }

    private void parseE() throws FeedParseException
    {
        switch (pointer){
            case "{":
                //STORES 1-ELEMENT OF FEED
                ArrayList<String> element = new ArrayList<String>();

                movePointer();

                element = parseF(element);


                //UPON-RETURN
                output.add(element);

                switch(pointer){
                    case "}":
                        parseE2();
                }
                //EMPTY-CASE
                //goes back to S/E2
                break;
            case "]":
                return;

            default:
                throw new FeedParseException();
        }
    }

    private void parseE2() throws FeedParseException
    {
        switch(pointer){
            case ",":
                movePointer();
                parseE();

                //EMPTY-CASE
                //goes back to E
                break;
            case "]":
                return;

            default:
                throw new FeedParseException();
        }
    }
    //GIT
    private ArrayList<String> parseF(ArrayList<String> element)
    {
        String temp = "";

        if (!(pointer.equals("}"))){

            //TODO: @Test: test DFA!
            /**
             *FDA
             */
            while (!(pointer.equals(",")) || !(pointer.equals("}"))){

                temp += pointer;

                movePointer();
            }
            //Removes ""-quotations//TODO:<--May cause reomove-data in BODY-field!!
            temp.replaceAll("\"", "");
            element.add(temp);
            /**
             * END
             */


            if (!(pointer.equals("}"))){
                try {
                    parseF2(element);
                }
                catch (FeedParseException e){}
            }
            else{
                //EMPTY-CASE
                //goes back to E/F2
                return element;
            }
        }
        else{
            //EMPTY-CASE
            //goes back to E/F2
            return element;
        }

        //needed...
        return element;
    }

    private ArrayList<String> parseF2(ArrayList<String> element) throws FeedParseException
    {
        switch (pointer){
            case ",":
                movePointer();
                parseF(element);

                //EMPTY CASE
                //goes back to F
                break;
            case "}":
                return element;

            default:
                throw new FeedParseException();
        }
        //needed...
        return element;
    }


    /**
     * MOVES TO NEXT LETTER
     */
    private void movePointer()
    {
        try {
            pointer = Integer.toString(stream.read());
        }
        catch(IOException e){}
    }

    /**
     * CLOSES RESOURCE
     */
    private void close()
    {
        stream.close();
    }

    /**
     * OUTPUT
     */
    private ArrayList<ArrayList<String>> output()
    {
        //TODO: @Test: see that JSoup works fine here (no nullPexception!!).
        //FORMATTING OUTPUT\\//
        try {
            String[] temp = null;
            Document body = null;
            for (ArrayList<String> element : output){
                for (String field : element){
                    temp = field.split(":");
                    if (temp[0].equals("Body")){

                        /**
                         * PER-BODY ELEMENT PER ITEM!!!!--\/
                         */
                        body = Jsoup.parse(temp[1]);
                        Elements paragraphs = body.getElementsByTag("p");
                        String bodyText = "";
                        for (Element paragraph : paragraphs){
                            bodyText += paragraph.text();
                        }
                        //output\\//
                        field = temp[0]+":"+bodyText;

                    }
                }
            }
        }
        catch(NullPointerException e){}

        return output;
    }
}