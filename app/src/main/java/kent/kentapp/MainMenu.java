package kent.kentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_menu);

        final Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });

        final Button btnSDS = (Button) findViewById(R.id.btnSDS);
        btnSDS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, SDS.class);
                startActivity(intent);
                //finish();
            }
        });

        final Button btnMoodle = (Button) findViewById(R.id.btnMoodle);
        btnMoodle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Moodle.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}
