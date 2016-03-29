package kent.kentapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    ArrayList<String> recFriends = new ArrayList<>();
    ArrayList<String> friends = new ArrayList<>();


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


        Button friendBtn = (Button) findViewById(R.id.friendBtn);
        friendBtn.setOnClickListener((View.OnClickListener) this);
        GridLayout.LayoutParams params = (GridLayout.LayoutParams) friendBtn.getLayoutParams();
        Button friendProfile;
        GridLayout gridLayout3 = (GridLayout) findViewById(R.id.gridLayout3);

        for (String s : friends) {
            friendProfile = new Button(this);
            friendProfile.setLayoutParams(params);
            //friendProfile.setBackground(recFriends.get(i));
            gridLayout3.addView(friendProfile);
        }


        Button recFriendBtn = (Button) findViewById(R.id.recommendedFriendBtn);
        recFriendBtn.setOnClickListener((View.OnClickListener) this);
        GridLayout.LayoutParams recFriend = (GridLayout.LayoutParams) friendBtn.getLayoutParams();
        Button rFriendProfile;
        GridLayout gridLayout2 = (GridLayout) findViewById(R.id.gridLayout2);

        for (String rf : recFriends) {
            rFriendProfile = new Button(this);
            rFriendProfile.setLayoutParams(recFriend);
            //friendProfile.setBackground(recFriends.get(i));
            gridLayout2.addView(rFriendProfile);
        }

        Typeface verdanna = Typeface.createFromAsset(getAssets(), "fonts/verdana.ttf");
        TextView title = (TextView) findViewById(R.id.friends_title);
        title.setTypeface(verdanna);
    }


    public void onClick(View menu) {
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
                Intent friendPageOpen = new Intent(Friends.this, FriendProfile.class);
                startActivity(friendPageOpen);
                //finish();
                break;

            case R.id.recommendedFriendBtn:
                Intent recFriendPage = new Intent(Friends.this, FriendProfile.class);
                startActivity(recFriendPage);
                //finish();
                break;


        }

    }

}
