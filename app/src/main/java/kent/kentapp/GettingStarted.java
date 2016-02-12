package kent.kentapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;


public class GettingStarted extends Activity {

    CheckBox it_account_checkbox, fees_checkbox, accommodation_checkbox, immunisation_checkbox, wellbeing_checkbox;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);


        it_account_checkbox = (CheckBox) findViewById(R.id.it_account_checkbox);
        fees_checkbox = (CheckBox) findViewById(R.id.fees_checkbox);
        accommodation_checkbox = (CheckBox) findViewById(R.id.accommodation_checkbox);
        immunisation_checkbox = (CheckBox) findViewById(R.id.immunisation_checkbox);
        wellbeing_checkbox = (CheckBox) findViewById(R.id.optional_check);

        continueButton = (Button) findViewById(R.id.proceed_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent proceed = new Intent(GettingStarted.this, MainMenu.class);

                if (it_account_checkbox.isChecked() && fees_checkbox.isChecked() && accommodation_checkbox.isChecked()
                   && immunisation_checkbox.isChecked()) {

                   startActivity(proceed);

                }
                else

                {
                    showToast();
                }
            }
        });
    }



    public void showToast() {
        Toast.makeText(this, "Please complete all previous tasks before continuing", Toast.LENGTH_LONG).show();
    }

}