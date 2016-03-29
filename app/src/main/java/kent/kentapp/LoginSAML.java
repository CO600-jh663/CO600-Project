package kent.kentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginSAML extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_loginsaml);

        CookieManager cookieManager = CookieManager.getInstance();
        if(cookieManager.getCookie("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp") == null) {
            WebView webview = new WebView(this);
            setContentView(webview);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    System.out.println(url);
                    if(url.equalsIgnoreCase("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp")) {
                        Intent intent = new Intent(LoginSAML.this, Profile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    return false;
                }
            });
            webview.getSettings().setJavaScriptEnabled(true);

            webview.loadUrl("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp");
        }
        else {
            Intent intent = new Intent(LoginSAML.this, Profile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onBackPressed() {

    }
}
