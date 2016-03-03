package kent.kentapp;

import android.view.View;
import java.net.*;

public class FeedHTListener implements View.OnClickListener {

    //GIT
    private String url;

    public FeedHTListener(String addr)
    {
       url = addr;
    }

    public void onClick(View v)
    {
        try {
            HttpURLConnection connection = (HttpURLConnection)(new URL(url)).openConnection();

            //TODO: Send document to web-browser somehow...

            connection.disconnect();
        } catch (Exception e){}
    }
}
