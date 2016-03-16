package kent.kentapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.util.Log;
import android.os.Bundle;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class Calendar extends AppCompatActivity {
    private static final String TAG = "Calender";


    private SlidingUpPanelLayout slidingLayout;
    private WebView webView_content;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);
        slidingLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });

        slidingLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });





        webView_content = (WebView) findViewById(R.id.webView_content);
        webView_content.setWebViewClient(new MyWebViewClient());


        String url = "https://www.kent.ac.uk/student/my-study/";
        webView_content.loadUrl(url);
        webView_content.getSettings().setJavaScriptEnabled(true);
            //set layout slide listener


            final ImageButton socialBtn = (ImageButton) findViewById(R.id.socialBtn);
            socialBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar.this, Social.class);
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

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view1, String url) {
            view1.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (slidingLayout != null &&
                (slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

}
