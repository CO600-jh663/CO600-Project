package kent.kentapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<FeedElement> feed;


    //Constructor
    public EventsAdapter(Context context, ArrayList<FeedElement> events) {
        super();
        this.context = context;
        feed = events;
    }


    /**
     * IMPLEMENTED METHODS
     */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        FeedEvents events = (FeedEvents) feed.get(position);

        //ROW
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.news_template, parent);

        //VIEWS
        TextView title = (TextView) row.findViewById(R.id.etitle);
        title.setText(events.getTitle());

        if (events.hasTickets()) {
            TextView pop = (TextView) row.findViewById(R.id.eisempty);
            pop.setVisibility(View.VISIBLE);
            String tmp = "NO MORE TICKETS";
            pop.setText(tmp);
        }

        TextView body = (TextView) row.findViewById(R.id.ebody);
        body.setText(events.getDescription());


        //ImageView image = (ImageView)row.findViewById(R.id.eimg);
        //Document doc = null;
        //try {
        //    doc = Jsoup.connect(events.getImUrl()).timeout(10000).ignoreContentType(true).get();
        //}
        //catch (Exception e){Log.e("", e.getMessage());}
        //InputStream stream = (InputStream)doc.getElementsByTag("body").text();
        //image.setImageBitmap(BitmapFactory.decodeStream());

        return row;
    }

    @Override
    public int getViewTypeCount() {
        //There are 2-types of View: image, text.
        return 2;
    }

    public long getItemId(int position) {
        //SYNCHRONISED
        return position;
    }

    public FeedElement getItem(int position) {
        //Mhmm... yep
        return feed.get(position);
    }

    public int getCount() {
        //yep...
        return feed.size();
    }

    //REFRESHES ADAPTER
    public void refresh(ArrayList<FeedElement> newFeed) {
        feed = newFeed;
        notifyDataSetChanged();
    }
}
