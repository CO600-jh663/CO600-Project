package kent.kentapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Calendar;

public class Media extends AppCompatActivity{

    ImageButton uploadButton;
    ImageView imageToView;
    private static int RESULT_LOAD_IMAGE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);


        final ImageButton newsBtn = (ImageButton) findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Media.this, News.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Media.this, Calendar.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton socialBtn = (ImageButton) findViewById(R.id.socialBtn);
        socialBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Media.this, Social.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton mapBtn = (ImageButton) findViewById(R.id.mapsBtn);
        mapBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Media.this, CampusMap.class);
                startActivity(intent);
                //finish();
            }
        });

        final ImageButton moreBtn = (ImageButton) findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Media.this, More.class);
                startActivity(intent);
                //finish();
            }
        });

        uploadButton = (ImageButton) findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, RESULT_LOAD_IMAGE);


            }

        });

    }

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

            uploadButton.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }




}
