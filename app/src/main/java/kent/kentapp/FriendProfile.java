package kent.kentapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendProfile extends AppCompatActivity {

    ArrayList<String> friends = new ArrayList<>();
    ArrayList<String> students = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

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

        final TextView username = (TextView) findViewById(R.id.user_name);


        final ImageButton profilePic = (ImageButton) findViewById(R.id.profile_pic);
        profilePic.setOnLongClickListener((View.OnLongClickListener) this);


        final ImageButton btnAdd = (ImageButton) findViewById(R.id.add_friend);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String addedFriend = username.getText().toString();
                friends.add(addedFriend);
                //finish();
            }
        });


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
    }

    public void onClick(View menu) {
        switch (menu.getId()) {

            case R.id.newsBtn:
                Intent news = new Intent(FriendProfile.this, News.class);
                startActivity(news);
                //finish();
                break;

            case R.id.calendarBtn:
                Intent calendar = new Intent(FriendProfile.this, Calendar.class);
                startActivity(calendar);
                //finish();
                break;

            case R.id.socialBtn:
                Intent social = new Intent(FriendProfile.this, Social.class);
                startActivity(social);
                //finish();
                break;

            case R.id.mapsBtn:
                Intent maps = new Intent(FriendProfile.this, CampusMap.class);
                startActivity(maps);
                //finish();
                break;

            case R.id.moreBtn:
                Intent more = new Intent(FriendProfile.this, More.class);
                startActivity(more);
                //finish();
                break;

            case R.id.add_friend:
                Intent add_friend = new Intent(FriendProfile.this, More.class);
                startActivity(add_friend);
                //finish();
                break;
        }

    }

    //final GridLayout pageLayout = (GridLayout) findViewById(R.id.gridLayout);
    // @SuppressLint("NewApi")
    //@Override
    // public boolean onLongClick(View menu) {
    //switch (menu.getId()) {


    //case R.id.profile_pic:
    //pageLayout.setBackground(getExternalMediaDirs()].getDrawable());

    //return true;
    //break;

//});
}

