package kent.kentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class News extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_temp);

        WebView webview = (WebView) findViewById(R.id.web_view);
        webview.setVisibility(View.GONE);
        //setContentView(webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                if(view.getUrl().equals("https://www.kentunion.co.uk/")) {
                    view.loadUrl("javascript:"
                            + "var FunctionOne = function () {"
                            + "  try{document.querySelector('#_MENU_COLUMN').parentElement.removeChild(document.querySelector('#_MENU_COLUMN'));}catch(e){}"
                            + "  try{document.querySelector('#_TOP_BAR_CONTAINER').parentElement.removeChild(document.querySelector('#_TOP_BAR_CONTAINER'));}catch(e){}"
                            + "  try{document.querySelector('#apps_container').parentElement.removeChild(document.querySelector('#apps_container'));}catch(e){}"
                            + "  try{document.querySelector('#_CONTENT_COLUMN').setAttribute('style', 'padding-top: 0px;');}catch(e){}"
                            + "};"
                            + "FunctionOne();");
                    view.setVisibility(View.VISIBLE);
                }
                else {
                    view.loadUrl("javascript:"
                            + "var FunctionTwo = function () {"
                            + "  var y = document.getElementsByTagName('a');"
                            + "  var i;"
                            + "  for (i = 0; i < y.length; i++) {"
                            + "   try{y[i].parentElement.removeChild(y[i]);}catch(e){}"
                            + "  }"
                            + "  try{document.querySelector('#_MENU_COLUMN').parentElement.removeChild(document.querySelector('#_MENU_COLUMN'));}catch(e){}"
                            + "  try{document.querySelector('#_TOP_BAR_CONTAINER').parentElement.removeChild(document.querySelector('#_TOP_BAR_CONTAINER'));}catch(e){}"
                            + "  try{document.querySelector('#apps_container').parentElement.removeChild(document.querySelector('#apps_container'));}catch(e){}"
                            + "  try{document.querySelector('#_CONTENT_COLUMN').setAttribute('style', 'padding-top: 0px;');}catch(e){}"
                            + "};"
                            + "FunctionTwo();");
                }
            }
        });

        String url = "https://www.kentunion.co.uk/";
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);

        final ImageButton computerBtn = (ImageButton) findViewById(R.id.computerBtn);
        computerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(News.this, FreePc.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(News.this, Calendar.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton mapBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(News.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(News.this, More.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}
