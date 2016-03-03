package kent.kentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageButton;

public class More  extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

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

        }
}
