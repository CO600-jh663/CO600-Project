package kent.kentapp;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class Profile extends AppCompatActivity {


    ImageButton profilePicture; //= (ImageButton) findViewById(R.id.profile_picture);
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, News.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Calendar.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton computerBtn = (ImageButton) findViewById(R.id.computerBtn);
        computerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, FreePc.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton mapBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, More.class);
                startActivity(intent);
                //finish();
            }
        });

        profilePicture = (ImageButton) findViewById(R.id.profile_picture);
        profilePicture.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                return true;
            }
        });

        final Button btnSDS = (Button) findViewById(R.id.btnSDS);
        btnSDS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, SDS.class);
                startActivity(intent);
                //finish();
            }
        });

        final Button btnMoodle = (Button) findViewById(R.id.btnMoodle);
        btnMoodle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Moodle.class);
                startActivity(intent);
                //finish();
            }
        });

        final Button btnMail = (Button) findViewById(R.id.btnMail);
        btnMail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Email.class);
                startActivity(intent);
                //finish();
            }
        });



        EditText degree = (EditText) findViewById(R.id.add_degree);
    }


   // protected void onSaveInstanceState (Bundle outState){

        //super.onSaveInstanceState (outState);
        //Log.i ("Instance State", "onSaveInstanceState");
    //}


    //protected void onRestoreInstanceState (Bundle outState){

       // super.onRestoreInstanceState (outState);
       // Log.i ("Instance State", "onRestoreInstanceState");
    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            profilePicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }//
}



