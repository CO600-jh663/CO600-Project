package kent.kentapp;

/**
 * Created by Mike on 14/12/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;


public class NewsEventFeed extends  Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("News Feed");
        tabSpec.setContent(R.id.newsTab);
        tabSpec.setIndicator("News Feed");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Events Feed");
        tabSpec.setContent(R.id.eventsTab);
        tabSpec.setIndicator("Events Feed");
        tabHost.addTab(tabSpec);
    }
}
