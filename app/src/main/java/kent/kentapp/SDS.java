package kent.kentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class SDS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sds);
        WebView webview = new WebView(this);
        setContentView(webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://sds.kent.ac.uk/account/");
    }
}
