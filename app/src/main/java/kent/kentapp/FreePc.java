package kent.kentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class FreePc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_pc);

        WebView webview = (WebView) findViewById(R.id.web_view);
        webview.setVisibility(View.GONE);
        //setContentView(webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                view.loadUrl("javascript:"
                        + "var FunctionOne = function () {"
                        + "  var r = $.Deferred();"
                        + "  try{document.getElementsByTagName('header')[0].style.display='none';}catch(e){}"
                        + "  try{document.getElementsByTagName('header')[1].style.display='none';}catch(e){}"
                        + "  try{document.getElementsByClassName('daedalus-block')[0].style.display='none';}catch(e){}"
                        + "  try{document.getElementsByClassName('span4')[0].style.display='none';}catch(e){}"
                        + "  try{document.querySelector(\"#tonbridge > h3\").style.display='none';}catch(e){}"
                        + "  try{document.querySelector(\"#tonbridge > ul\").style.display='none';}catch(e){}"
                        + "  try{document.getElementsByClassName('global-footer')[0].style.display='none';}catch(e){}"
                        + "};"
                        + "FunctionOne();");
                view.setVisibility(View.VISIBLE);
            }
        });

        String url = "https://www.kent.ac.uk/student/studying/pcrooms/index.html";
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);

        final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FreePc.this, News.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FreePc.this, Calendar.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton mapBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FreePc.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FreePc.this, More.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}
