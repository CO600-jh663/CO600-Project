package kent.kentapp;

import android.os.*;
import android.app.*;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;


public class FeedActivity extends ListActivity
{
    //The state of the listView
    private String state;
    private FeedStore store;
    //ADAPTER IS INSTANTIATED IN ON-CREATE()
    private Button change;


    public FeedActivity()
    {
        super();
        //DEFAULT---------------\/
        store = new FeedStore(this);
        state = "news";

        change = (Button)findViewById(R.id.change);
    }


    @Override
    protected void onCreate(Bundle status)
    {
        super.onCreate(status);


        //Now activity knows' where listView is
        setContentView(R.layout.activity_main_menu1);

        /**
         * ADAPTER-FIELD--\/
         */
        final FeedAdapter adapter = new FeedAdapter(this, store, state);
        //TODO: \/--this BINDS adapter to listView :)
        setListAdapter(adapter);

        //TODO: @Test
        //\/-Button to change STATE
        change.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        //Clear old data.
                        ArrayList<ArrayList<String>> data = null;

                        //call FEEDSTORE to change state!
                        if (state.equals("news")) {
                            state = "events";

                            //data = store.get(state);
                            change.setText(state);
                        }
                        else if (state.equals("events")){
                            state = "news";

                            //data = store.get(state);
                            change.setText(state);
                        }
                        //then call FEEDADAPTER to change row-view for list.
                        adapter.refresh(data);
                    }
                }
        );
    }

    @Override
    public void onListItemClick(ListView view, View row, int position, long id)
    {
        //TODO: Not necessary right now.
    }

    //GIT
    public void destroy()
    {
        //Destroys activity-instance
        super.onDestroy();
    }
}