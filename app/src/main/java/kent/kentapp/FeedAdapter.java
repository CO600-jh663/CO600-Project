package kent.kentapp;

import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.*;
import android.content.*;

import java.util.*;
import java.net.*;

public class FeedAdapter extends BaseAdapter {
    private Context context;
    private HttpURLConnection client;
    private ArrayList<ArrayList<String>> feed;
    private String state;


    public FeedAdapter(Context context, ArrayList<ArrayList<String>> data, String type) {
        super();

        this.context = context;
        feed = data;
        state = type;

        //for now...
        client = null;
    }


    /**
     * IMPLEMENTED METHODS
     */
    //TODO: @Test
    public View getView(int position, View convertView, ViewGroup parent) {
        View newsRow = null;
        View eventsRow = null;

        if (convertView == null) {
            //Used to reference XML-files.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (state.equals("news")) {
                newsRow = inflater.inflate(R.layout.news_template, parent, false);
            } else if (state.equals("events")) {
                eventsRow = inflater.inflate(R.layout.events_template, parent, false);
            }
        } else {
            //RECYCLING
            newsRow = convertView;
            eventsRow = convertView;
        }

        //Get element from model
        ArrayList<String> element = feed.get(position);

        //Because of 'findVieByID'
        if (state.equals("news")) {
            //populate 'newsRow' with 'element' fields.
            for (String field : element) {
                String[] tuple = field.split(":");
                switch (tuple[0]) {
                    case "Title":
                        TextView ntitle = (TextView) newsRow.findViewById(R.id.ntitle);
                        ntitle.setText(tuple[1]);
                        break;
                    case "Body":
                        TextView nbody = (TextView) newsRow.findViewById(R.id.nbody);
                        nbody.setText(tuple[1]);
                        break;
                    case "ImageUrl":
                        //TODO: @Test
                        ImageView nimage = (ImageView) newsRow.findViewById(R.id.nimg);
                        try {
                            client = (HttpURLConnection) (new URL(tuple[1])).openConnection();
                            client.setRequestMethod("GET");
                            //----------------------------------------\/--THIS ONE WILL CAUSE ERRORS!!
                            nimage.setImageDrawable((Drawable) client.getContent());
                            client.disconnect();
                        } catch (Exception e) {
                        }
                        break;
                    case "Url":
                        try {
                            Button nbutton = (Button) newsRow.findViewById(R.id.nbutton);
                            nbutton.setOnClickListener(new FeedHTListener(tuple[1]));
                        } catch (Exception e) {
                        }
                        break;
                    case "PublishedDate":
                        String[] timeNdate = tuple[1].split("T");
                        //TODO: Not sure what to do w/ time of news...
                }
                //Output
                return newsRow;
            }
            //output
            return newsRow;
        } else if (state.equals("events")) {
            // w/r fields
            for (String field : element) {
                String[] tuple = field.split(":");
                // field-selection
                switch (tuple[0]) {
                    case "Description":
                        TextView etitle = (TextView) eventsRow.findViewById(R.id.etitle);
                        etitle.setText(tuple[1]);
                        break;
                    case "Body":
                        TextView ebody = (TextView) eventsRow.findViewById(R.id.ebody);
                        ebody.setText(tuple[1]);
                        break;
                    case "HasTickets":
                        if (tuple[1].equals("false")) {
                            TextView notice = (TextView) eventsRow.findViewById(R.id.eisempty);
                            //pop-up like
                            notice.setVisibility(View.VISIBLE);
                            notice.setText("NO MORE TICKETS");
                        }
                        break;
                    case "ImageUrl":
                        //TODO: @Test
                        ImageView eimage = (ImageView) newsRow.findViewById(R.id.eimg);
                        try {
                            client = (HttpURLConnection) (new URL(tuple[1])).openConnection();
                            client.setRequestMethod("GET");
                            //----------------------------------------\/--THIS ONE WILL CAUSE ERRORS!!
                            eimage.setImageDrawable((Drawable) client.getContent());
                            client.disconnect();
                        } catch (Exception e) {
                        }
                }
                //output
                return eventsRow;
            }
        }
        //if there's a state problem
        //which there'll never be... hopefully
        return convertView;
    }

    /**
     * Implemented methods-\/
     */
    @Override
    public int getViewTypeCount() {
        //There are 3-types of View image, text and button.
        return 3;
    }
    //GIT
    public long getItemId(int position) {
        //SYNCHRONISED
        return position;
    }

    public ArrayList<String> getItem(int position) {
        //I hope all this shit works...
        return feed.get(position);
    }

    public int getCount() {
        //Mhmm....
        return feed.size();
    }


    //REFRESHES ADAPTER
    //TODO: @Test
    public void refresh(ArrayList<ArrayList<String>> newFeed) {
        feed = newFeed;
        notifyDataSetChanged();
    }
}