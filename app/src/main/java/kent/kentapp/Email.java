package kent.kentapp;

import android.content.Intent;
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
        webView.setWebViewClient(new MyWebViewClient());

        String url = "https://outlook.office.com/owa/?realm=kent.ac.uk";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton mapsBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapsBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton socialBtn = (ImageButton) findViewById(R.id.socialBtn);
        socialBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener((View.OnClickListener) this);
    }

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

            case R.id.socialBtn:
                Intent social = new Intent(Email.this, Social.class);
                startActivity(social);
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

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
