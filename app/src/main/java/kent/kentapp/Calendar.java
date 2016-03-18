package kent.kentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
 
public class Calendar extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);

            WebView webview = (WebView) findViewById(R.id.web_view);
            webview.setVisibility(View.GONE);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url)
                {
                    view.loadUrl("javascript:"
                            + "var FunctionOne = function () {"
                            + "  var r = $.Deferred();"
                            + "  try{document.getElementById('kent-bar').style.display='none';}catch(e){}"
                            + "  try{document.getElementsByClassName('row-fluid')[0].style.display='none';}catch(e){}"
                            + "  try{document.querySelector(\"#tpl_calendar > div.calendar-links.visible-phone.well > ul\").style.display='none';}catch(e){}"
                            + "  try{document.querySelector(\"#calendar-disclaimer > div > div > a\").removeAttribute(\"href\");}catch(e){}"
                            + "};"
                            + "FunctionOne();");
                    view.setVisibility(View.VISIBLE);
                }
            });

            String url = "https://www.kent.ac.uk/student/my-study/";
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);

            final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
            newsBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar.this, News.class);
                    startActivity(intent);
                    //finish();
                }
            });

            final ImageButton computerBtn = (ImageButton) findViewById(R.id.computerBtn);
            computerBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar.this, FreePc.class);
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
}
