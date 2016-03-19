package kent.kentapp;

import android.content.Context;
import android.util.Log;

import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.util.*;


public class FreePCs implements Runnable
{
    private final String address = "https://api.kent.ac.uk/api/v1/pcs/canterbury/free?jsonp";
    private FeedParser parser;
    private String buildingsFeed;
    private FreePCActivity activity;


    //FeedStore thus controls freePCs
    public FreePCs(FreePCActivity a, FeedParser p)
    {
        activity = a;
        parser = p;
    }


    //for REFRESHING feed every 5-seconds
    public void run()
    {
        while(true) {
            //Get feed
            try {
                Document file = Jsoup.connect(address).timeout(10000).ignoreContentType(true).get();
                //OUTPUT
                buildingsFeed = file.getElementsByTag("body").text();
                //Automatically Disconnects...
            } catch (Exception e) {
                Log.e("FeedClientException", e.getMessage());
            }

            //Parse feed
            renderList();

            //wait 10-seconds!...
            try {
                this.wait(10000);
            }
            catch(InterruptedException e){Log.e("", e.getMessage());}

            //start again
        }
    }

    private void renderList()
    {
        ArrayList<ArrayList<String>> out;

        //remove '('&');'--------------TODO: Make sure it does-----\/
        buildingsFeed = buildingsFeed.substring(1, (buildingsFeed.length() - 2));
        //Parse
        out = parser.parseThis(buildingsFeed);
        parser.refresh();

        //@TEST
        int x = 0;

        activity.pc(map(out));
    }

    private ArrayList<FPCelement> map(ArrayList<ArrayList<String>> parsed)
    {
        ArrayList<FPCelement> puts = new ArrayList<FPCelement>();
        String[] split;
        FPCelement pcel;

        for (ArrayList<String> element : parsed){
            pcel = new FPCelement();
            for (String field : element){
                split = field.split(":");
                switch (split[0]){
                    case "name":
                        pcel.addField(split[0], split[1]);
                        break;
                    case "free":
                        pcel.addField(split[0], split[1]);
                }
            }
            puts.add(pcel);
        }
        return puts;
    }
}