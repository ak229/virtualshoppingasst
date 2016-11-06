package ub.tuere.virtualshoppingasst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistrationForm extends AppCompatActivity {
    String layout_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            layout_name = extras.getString("Filename");
        }

        if(layout_name.equals("profile_home")){
            setContentView(R.layout.personal_registration_form);
        }
        else if(layout_name.equals("profile_work")){
            setContentView(R.layout.personal_registration_form);
        }
        else if(layout_name.equals("profile_basic")){
            setContentView(R.layout.personal_registration_form);
        }
        else {
            setContentView(R.layout.personal_registration_form);
        }
    }
}
