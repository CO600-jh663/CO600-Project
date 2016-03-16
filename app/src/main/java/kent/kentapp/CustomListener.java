package kent.kentapp;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CustomListener implements View.OnClickListener
{
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToView;
    ImageButton uploadButton, downloadButton;

    public CustomListener(ImageView img)
    {
        imageToView = img;
    }

    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.uploadButton:

                //code for uploading
                //This will allow the gallery to be open
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ActivityCompat.startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE );


                break;

            case R.id.downloadButton:
                //code for downloading

                break;
        }
    }



}
