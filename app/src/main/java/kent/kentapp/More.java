package kent.kentapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class More  extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        final TextView profileText = (TextView) findViewById(R.id.profile_sub_link);
        final ImageView profileImage = (ImageView) findViewById(R.id.profileimg);
        View.OnClickListener profileOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Profile.class);
                startActivity(intent);
            }
        };
        profileText.setOnClickListener(profileOnClickListener);
        profileImage.setOnClickListener(profileOnClickListener);

        final TextView friendsText = (TextView) findViewById(R.id.friends_sub_link);
        final ImageView friendsImage = (ImageView) findViewById(R.id.friends_img);
        View.OnClickListener friendsOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Friends.class);
                startActivity(intent);
            }
        };
        friendsText.setOnClickListener(friendsOnClickListener);
        friendsImage.setOnClickListener(friendsOnClickListener);

        final TextView unionText = (TextView) findViewById(R.id.kent_union_sub_link);
        final ImageView unionImage = (ImageView) findViewById(R.id.ku_img);
        View.OnClickListener unionOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(More.this, KentUnion.class);
                //startActivity(intent);
            }
        };
        unionText.setOnClickListener(unionOnClickListener);
        unionImage.setOnClickListener(unionOnClickListener);

        final TextView mediaText = (TextView) findViewById(R.id.media_sub_link);
        final ImageView mediaImage = (ImageView) findViewById(R.id.media_img);
        View.OnClickListener mediaOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(More.this, Media.class);
                //startActivity(intent);
            }
        };
        mediaText.setOnClickListener(mediaOnClickListener);
        mediaImage.setOnClickListener(mediaOnClickListener);

        final TextView directoryText = (TextView) findViewById(R.id.directory_sub_link);
        final ImageView directoryImage = (ImageView) findViewById(R.id.directory_img);
        View.OnClickListener directoryOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Directory.class);
                startActivity(intent);
            }
        };
        directoryText.setOnClickListener(directoryOnClickListener);
        directoryImage.setOnClickListener(directoryOnClickListener);

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

        final ImageButton socialBtn = (ImageButton) findViewById(R.id.socialBtn);
        socialBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Social.class);
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
