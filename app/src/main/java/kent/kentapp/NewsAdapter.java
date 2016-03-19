package kent.kentapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.util.*;
import java.net.*;
import java.util.zip.Inflater;

public class NewsAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<FeedElement> feed;


    //Constructor
    public NewsAdapter(Context context, ArrayList<FeedElement> news)
    {
        super();
        this.context = context;
        feed = news;
    }


    /**
     * IMPLEMENTED METHODS
     */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        FeedNews news = (FeedNews)feed.get(position);

        //ROW
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.news_template,  parent);

        //VIEWS
        TextView title = (TextView)row.findViewById(R.id.ntitle);
        title.setText(news.getTitle());

        TextView body = (TextView)row.findViewById(R.id.nbody);
        body.setText(news.getLeader());

        //ImageView image = (ImageView)row.findViewById(R.id.nimg);
        //Document doc = null;
        //try {
        //    doc = Jsoup.connect(news.getImUrl()).timeout(10000).ignoreContentType(true).get();
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