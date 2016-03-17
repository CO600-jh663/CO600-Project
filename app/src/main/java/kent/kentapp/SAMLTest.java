package kent.kentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SAMLTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_samltest);
        WebView webview = new WebView(this);
        setContentView(webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println(url);
                if(url.equalsIgnoreCase("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp")) {
                    Intent intent = new Intent(SAMLTest.this, Profile.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp");
        System.out.println("ok");
    }
}
