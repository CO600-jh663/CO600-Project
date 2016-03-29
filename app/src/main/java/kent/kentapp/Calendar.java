package kent.kentapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);

            Typeface verdanna = Typeface.createFromAsset(getAssets(), "fonts/verdana.ttf");
            TextView title = (TextView) findViewById(R.id.calendar_title);
            title.setTypeface(verdanna);

            WebView webView = (WebView) findViewById(R.id.web_view);
            webView.setWebViewClient(new MyWebViewClient());

            String url = "https://www.kent.ac.uk/student/my-study/";
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);

            final ImageButton socialBtn = (ImageButton) findViewById(R.id.socialBtn);
            socialBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar.this, Social.class);
                    startActivity(intent);
                    //finish();
                }
            });

            final ImageButton mapBtn = (ImageButton) findViewById(R.id.mapsBtn);
            mapBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar.this, CampusMap.class);
                    startActivity(intent);
                    //finish();
                }
            });

            final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
            moreBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar.this, More.class);
                    startActivity(intent);
                    //finish();
                }
            });
        }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
