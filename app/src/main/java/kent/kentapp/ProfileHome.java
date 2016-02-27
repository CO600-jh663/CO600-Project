package kent.kentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mike on 17/12/2015.
 */
public class ProfileHome extends AppCompatActivity

    {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        final Button btnMap = (Button) findViewById(R.id.map_hyp);
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHome.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });
    }
    }
