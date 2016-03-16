package kent.kentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    ArrayList<String> panel1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton mapsBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapsBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton socialBtn = (ImageButton) findViewById(R.id.socialBtn);
        socialBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener((View.OnClickListener) this);

        final ImageButton friendBtn = (ImageButton) findViewById(R.id.friendBtn);
        moreBtn.setOnClickListener((View.OnClickListener) this);

        panel1.add("1");
        panel1.add("2");

        RelativeLayout friends = (RelativeLayout) findViewById(R.id.friends);

        for (String s : panel1) {
            ImageButton friendProfile = new ImageButton(this);
            friendProfile.setImageResource(panel1.get(i));
            //friendProfile.setBackground();
            friendProfile.setBackgroundColor(0xFF99D6D6);
            friends.addView(friendProfile);
        }
    }

    public void onClick(View menu)
    {
        switch (menu.getId()) {

            case R.id.newsBtn:
                Intent news = new Intent(Friends.this, News.class);
                startActivity(news);
                //finish();
                break;

            case R.id.calendarBtn:
                Intent calendar = new Intent(Friends.this, Calendar.class);
                startActivity(calendar);
                //finish();
                break;

            case R.id.socialBtn:
                Intent social = new Intent(Friends.this, Social.class);
                startActivity(social);
                //finish();
                break;

            case R.id.mapsBtn:
                Intent maps = new Intent(Friends.this, CampusMap.class);
                startActivity(maps);
                //finish();
                break;

            case R.id.moreBtn:
                Intent more = new Intent(Friends.this, More.class);
                startActivity(more);
                //finish();
                break;

            case R.id.friendBtn:
                Intent friendPage = new Intent(Friends.this, FriendProfile.class);
                startActivity(friendPage);
                //finish();
                break;


        }

    }

}
