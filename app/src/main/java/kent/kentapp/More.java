package kent.kentapp;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;

public class More  extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        final Button btnMap = (Button) findViewById(R.id.map_hyp);
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(More.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });
    }

}
