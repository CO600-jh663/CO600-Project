package kent.kentapp;

import java.util.*;
import android.content.*;

public class FeedStore
{
    /**
     * TYPES OF ELEMENT
     */
    private ArrayList<FeedElement> news;
    private ArrayList<FeedElement> events;

    //parser
    FeedParser parser;
    //context
    Context context;



    public FeedStore(Context context)
    {
        parser = new FeedParser(this);
        this.context = context;


        //get lists
        news = map(parser.getFeed("news"));
        events = map(parser.getFeed("events"));
    }


    public ArrayList<FeedElement> get(String type)
    {
        if (type.equals("events")) {
            return events;
        }
        else{
            return news;
        }
    }

    private ArrayList<FeedElement> map(ArrayList<ArrayList<String>> data)
    {
        ArrayList<FeedElement> lst = new ArrayList<FeedElement>();
        FeedNews lmnt;
        String[] split;
        for (ArrayList<String> elmnt : data){

            //new element
            lmnt = new FeedNews();

            for (String fld : elmnt){
                split = fld.split(":");

                //Mapping
                lmnt.addField(split[0], split[1]);

            }

            //output element
            lst.add(lmnt);

        }
        //output
        return lst;
    }
}