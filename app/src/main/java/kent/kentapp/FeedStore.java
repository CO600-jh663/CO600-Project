package kent.kentapp;

import java.util.*;
import android.app.*;

public class FeedStore
{
    /**
     * TYPES
     */
    private ArrayList<ArrayList<String>> news;
    private ArrayList<ArrayList<String>> events;

    //STATE
    private String state;
    private FeedParser parser;


    public FeedStore(String state)
    {
        this.state = state;
        news = new ArrayList<ArrayList<String>>();
        events = new ArrayList<ArrayList<String>>();
        parser = new FeedParser(this);

        //GETS FEEDS!
        news = parser.getFeed("news");
        events = parser.getFeed("events");
    }


    public ArrayList<ArrayList<String>> get(String type)
    {
        if (type.equals("news")){
            return news;
        }
        else if (type.equals("events")){
            return events;
        }
        else{
            //DE-FAULT
            return null;
        }
    }

    /**
     * TODO: Ask Dragos when feeds' up-date!!!!
     */

    public String getState()
    {
        return state;
    }
}