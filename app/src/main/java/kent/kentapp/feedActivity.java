package kent.kentapp;

import android.os.*;
import android.app.*;
import android.widget.*;
import java.util.*;


public class FeedActivity extends ListActivity {

    private FeedStore store;

    public FeedActivity(FeedStore stored)
    {
        super();
        store = stored;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        //Activity link to XML-----------\/
        setContentView(android.R.layout.feed);


        //The ListView itself------------------------------------------\/
        final ListView listview = (ListView)findViewById(R.id.feed);


        //DATA
        ArrayList<ArrayList<String>> feed = store.getData();
        final ArrayList<String> temp = new ArrayList<String>();

        //TEST: gets header
        for (ArrayList<String> element : feed){
            for (String field : element){
                //only get title
                if (field.substring(1, 6).equals("title")){
                    temp.add(field.substring(8, field.length()));
                }
            }
        }


        //Adapter
        final ArrayAdapter<ArrayList<ArrayList<String>>> adapter = new ArrayAdapter<ArrayList<String>>(this, R.layout.activity_feed, android.R.id.title, temp);
        setListAdapter(adapter);


        //Listener:
        //----onListItemClick(,...);
    }
}