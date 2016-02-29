package kent.kentapp;

import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.util.*;
import java.net.*;

public class FeedAdapter extends BaseAdapter
{
    private Context context;
    private HttpURLConnection client;
    private ArrayList<ArrayList<String>> feed;


    public FeedAdapter(Context context, ArrayList<ArrayList<String>> data)
    {
        super();

        this.context = context;
        feed = data;

        //for now...
        client = null;
    }


    /**
     * IMPLEMENTED METHODS
     */
    //TODO: @Test
    public View getView(int position, View view, ViewGroup row)
    {
        if (row == null) {

            //ROOT-NODE-OF-ROW-TEMPLATE--\/
            RelativeLayout template = new RelativeLayout(context);
            //define template via random sample...
            ArrayList<String> source = feed.get(position);

            String[] temp = null;
            for (String field : source) {
                temp = field.split(":");

                /**
                 * FOR-EACH ROW CREATE APPROPRIATE VIEW
                 * TO ADD UNDER parent!
                 */
                switch (temp[0]) {
                    case "Title":
                        TextView title = new TextView(context);

                        title.setId(R.id.ftitle);
                        title.setText(temp[1]);
                        //TODO: make sure 1st-element UNDER parent!!

                        template.addView(title);
                        break;
                    case "Body":
                        TextView body = new TextView(context);
                        body.setId(R.id.body);
                        body.setText(temp[1]);
                        template.addView(body);
                        break;
                    case "ImageUrl":

                        //TODO: @Test
                        ImageView image = new ImageView(context);
                        image.setId(R.id.img);
                        try {
                            client = (HttpURLConnection) (new URL(temp[1])).openConnection();

                            client.setRequestMethod("GET");
                            //----------------------------------------\/--THIS ONE WILL CAUSE ERRORS!!
                            image.setImageDrawable((Drawable) client.getContent());

                            client.disconnect();
                        } catch (Exception e){}

                        template.addView(image);

                        break;

                    //Means event therefore OVERRIDES title
                    case "Description":
                        if (template.findViewById(R.id.ftitle) == null) {
                            title = new TextView(context);
                            title.setId(R.id.ftitle);
                            title.setText(temp[1]);
                        }
                        else{
                            title.setText(temp[1]);
                        }
                }
            }
            //output
            return template;
        }
        else {
            ArrayList<String> source = feed.get(position);
            String[] temp = null;
            for (String field : source) {
                temp = field.split(":");
                switch (temp[0]) {
                    case "Title":
                        TextView title = (TextView) row.findViewById(R.id.ftitle);
                        title.setText(temp[1]);
                        //TODO: make sure 1st-element UNDER parent!!
                        break;
                    case "Body":
                        TextView body = (TextView) row.findViewById(R.id.body);
                        body.setText(temp[1]);
                        break;
                    case "ImageUrl":
                        //TODO: @Test
                        ImageView image = row.findViewById(R.id.img);
                        try {
                            client = (HttpURLConnection) (new URL(temp[1])).openConnection();

                            client.setRequestMethod("GET");
                            //----------------------------------------\/--THIS ONE WILL CAUSE ERRORS!!
                            image.setImageDrawable((Drawable) client.getContent());

                            client.disconnect();
                        } catch (Exception e) {
                        }
                        break;

                    //Means event therefore OVERRIDES title
                    case "Description":
                        title = row.findViewById(R.id.ftitle);
                        title.setText(temp[1]);
                }
            }
            //RECYCLING
            return row;
        }
    }

    @Override
    public int getViewTypeCount()
    {
       //There are 2-types of View image and text.
        return 2;
    }

    public long getItemId(int position)
    {
        //SYNCHRONISED
        return position;
    }

    public Object getItem(int position)
    {
        //I hope all this shit works...
        return feed.get(position);
    }

    public int getCount()
    {
        //Mhmm....
        return feed.size();
    }


    //REFRESHES ADAPTER
    //TODO: @Test
    public void refresh(ArrayList<ArrayList<String>> newFeed)
    {
        feed = newFeed;
        notifyDataSetChanged();
    }
}