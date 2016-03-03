package kent.kentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
 
public class Calendar extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);

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
}
