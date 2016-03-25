package kent.kentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginSAML extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_loginsaml);
        WebView webview = new WebView(this);
        setContentView(webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println(url);
                if(url.equalsIgnoreCase("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp")) {
                    Intent intent = new Intent(LoginSAML.this, Profile.class);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finish();
                }
                return false;
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp");
        System.out.println("ok");
    }

    @Override
    public void onBackPressed() {

    }
}
