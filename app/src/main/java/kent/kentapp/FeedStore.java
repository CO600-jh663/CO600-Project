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
        news = map(parser.getFeed("news"), "n");
        parser.refresh();
        events = map(parser.getFeed("events"), "e");
        parser.refresh();
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

    private ArrayList<FeedElement> map(ArrayList<ArrayList<String>> data, String type)
    {
        ArrayList<FeedElement> lst = new ArrayList<FeedElement>();
        FeedElement lmnt;
        String[] split;
        for (ArrayList<String> elmnt : data){

            //new element
            if (type.equals("n")) {
                lmnt = new FeedNews();
            }
            else{
                lmnt = new FeedEvents();
            }

            for (String fld : elmnt){
                //Ignores empty-valued fields!!
                if (!(fld.substring((fld.length()-1),fld.length()).equals(":"))){
                    split = fld.split(":");

                    //Mapping
                    lmnt.addField(split[0], split[1]);
                }
            }

            //output element
            lst.add(lmnt);

        }
        //output
        return lst;
    }
}