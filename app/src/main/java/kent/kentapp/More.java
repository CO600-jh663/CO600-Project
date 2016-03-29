package kent.kentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class More  extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        final TextView profileText = (TextView) findViewById(R.id.profileBtn);
        View.OnClickListener profileOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Profile.class);
                startActivity(intent);
            }
        };
        profileText.setOnClickListener(profileOnClickListener);
        //profileImage.setOnClickListener(profileOnClickListener);

        final TextView friendsText = (TextView) findViewById(R.id.friendsBtn);
        View.OnClickListener friendsOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent intent = new Intent(More.this, Friends.class);
                startActivity(intent);*/
            }
        };
        friendsText.setOnClickListener(friendsOnClickListener);
       //friendsImage.setOnClickListener(friendsOnClickListener);

        final TextView eventsTxt = (TextView) findViewById(R.id.eventsBtn);
        View.OnClickListener eventsOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Events.class);
                startActivity(intent);
            }
        };
        eventsTxt.setOnClickListener(eventsOnClickListener);

        final TextView mediaText = (TextView) findViewById(R.id.mediaBtn);
        View.OnClickListener mediaOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Media.class);
                startActivity(intent);
            }
        };
        mediaText.setOnClickListener(mediaOnClickListener);

        final TextView directoryText = (TextView) findViewById(R.id.directoryBtn);
        View.OnClickListener directoryOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Directory.class);
                startActivity(intent);
            }
        };
        directoryText.setOnClickListener(directoryOnClickListener);

        final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, News.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Calendar.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton computerBtn = (ImageButton) findViewById(R.id.computerBtn);
        computerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, FreePc.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton mapBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });

        final Button logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebView webview = new WebView(More.this);
                //setContentView(webview);
                webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        System.out.println(url);
                        if(url.equalsIgnoreCase("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/logout.php")) {
                            CookieManager cookieManager = CookieManager.getInstance();
                            cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
                                @Override
                                public void onReceiveValue(Boolean value) {
                                    System.out.println("Remove all cookies: " + value);
                                }
                            });
                            Intent intent = new Intent(More.this, LoginSAML.class);
                            startActivity(intent);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            finish();
                        }
                        return false;
                    }
                });
                webview.getSettings().setJavaScriptEnabled(true);
                webview.loadUrl("https://raptor.kent.ac.uk/proj/co600/project/c26_fresher/simplesaml/module.php/core/authenticate.php?as=default-sp&logout");
                System.out.println("ok");

            }
        });

    }
}
