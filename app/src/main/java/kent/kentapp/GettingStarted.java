package kent.kentapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class GettingStarted extends Activity {

    CheckBox it_account_checkbox, fees_checkbox, accommodation_checkbox, immunisation_checkbox, wellbeing_checkbox;
    Button continueButton;
    SharedPreferences preferences = null;
    String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        preferences = getSharedPreferences("com.kent.kentapp", MODE_PRIVATE);

        it_account_checkbox = (CheckBox) findViewById(R.id.it_account_checkbox);
        fees_checkbox = (CheckBox) findViewById(R.id.fees_checkbox);
        accommodation_checkbox = (CheckBox) findViewById(R.id.accommodation_checkbox);
        immunisation_checkbox = (CheckBox) findViewById(R.id.immunisation_checkbox);
        wellbeing_checkbox = (CheckBox) findViewById(R.id.optional_check);

        it_account_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(isChecked) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(GettingStarted.this);
                    alertDialog.setTitle("Username");
                    alertDialog.setMessage("Please enter your IT Account Username");

                    final EditText input = new EditText(GettingStarted.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    alertDialog.setView(input);

                    alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    username = input.getText().toString();
                                    AsyncTask<String, Void, Boolean> task = new getRegistrationStatus();
                                    task.execute(username);
                                    try {
                                        if(task.get().equals(false)) {
                                            it_account_checkbox.setChecked(false);
                                            //dialog.cancel();
                                            Toast.makeText(GettingStarted.this, "Our records show you are not yet registered, please register to continue", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    it_account_checkbox.setChecked(false);
                                    dialog.cancel();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        continueButton = (Button) findViewById(R.id.proceed_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent proceed = new Intent(GettingStarted.this, Profile.class);

                if (it_account_checkbox.isChecked() && fees_checkbox.isChecked() && accommodation_checkbox.isChecked()
                        && immunisation_checkbox.isChecked()) {
                    preferences.edit().putBoolean("allChecked", true).commit();
                    startActivity(proceed);
                }
                else {
                    showToast();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            preferences.edit().putBoolean("firstrun", false).commit();
        }
        else {
            Intent proceed = new Intent(GettingStarted.this, Profile.class);
            if (preferences.getBoolean("allChecked", true)) {
                startActivity(proceed);
            }
            else {
                showToast();
            }
        }
    }

    public void showToast() {
        Toast.makeText(this, "Please complete all previous tasks before continuing", Toast.LENGTH_LONG).show();
    }

}

class getRegistrationStatus extends AsyncTask<String, Void, Boolean> {
    protected Boolean doInBackground(String... username) {

        String urlString = "http://raptor.kent.ac.uk/proj/co600/project/c26_fresher/KSAPP_db_get_registration_status.php?username=" + username[0];
        URL url;
        ArrayList response;
        try {
            url = new URL(urlString);
            HttpURLConnection urlConnection;
            response = new ArrayList();
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //urlConnection.setRequestProperty("username", username);

            System.out.println("\nSending request to URL : " + url);
            System.out.println("Response Code : " + urlConnection.getResponseCode());
            System.out.println("Response Message : " + urlConnection.getResponseMessage());

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.add(inputLine);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error fetching user data: " + e);
            return null;
        }
        if(response.get(0).equals("Registered")) return true;
        else return false;
    }
}