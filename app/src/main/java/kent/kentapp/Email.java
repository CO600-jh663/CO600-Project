package kent.kentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class Email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);


        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println(url);
                if(url.contains("https://outlook.office365.com/owa/?realm=kent.ac.uk")) return false;
                if(url.contains("https://sso.id.kent.ac.uk")) return false;
                if(url.contains("https://dan.kent.ac.uk")) return false;
                if(url.contains("https://login.microsoftonline.com")) return false;
                else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                    return true;
                }
            }
        });

        String url = "https://outlook.office.com/owa/?realm=kent.ac.uk";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        View.OnClickListener toolbarListener = new View.OnClickListener() {
            public void onClick(View menu) {
                switch (menu.getId()) {

                    case R.id.newsBtn:
                        Intent news = new Intent(Email.this, News.class);
                        startActivity(news);
                        //finish();
                        break;

                    case R.id.calendarBtn:
                        Intent calendar = new Intent(Email.this, Calendar.class);
                        startActivity(calendar);
                        //finish();
                        break;

                    case R.id.computerBtn:
                        Intent freePC = new Intent(Email.this, FreePc.class);
                        startActivity(freePC);
                        //finish();
                        break;

                    case R.id.mapsBtn:
                        Intent maps = new Intent(Email.this, CampusMap.class);
                        startActivity(maps);
                        //finish();
                        break;

                    case R.id.moreBtn:
                        Intent more = new Intent(Email.this, More.class);
                        startActivity(more);
                        //finish();
                        break;
                }
            }
        };

        final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(toolbarListener);

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(toolbarListener);

        final ImageButton mapsBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapsBtn.setOnClickListener(toolbarListener);

        final ImageButton freePcBtn = (ImageButton) findViewById(R.id.computerBtn);
        freePcBtn.setOnClickListener(toolbarListener);

        final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(toolbarListener);
    }
}
