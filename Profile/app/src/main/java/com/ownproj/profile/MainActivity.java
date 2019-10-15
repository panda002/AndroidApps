package com.ownproj.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_IMAGE = "flagimage";
    public static final String PROFILE="myProfile";
    private EditText frname;
    private EditText lsname;
    private Button save_bt;
    private RadioGroup radioSex1;
    private RadioButton radioFmale1;
    private RadioButton radioMale1;
    private ImageView imageView1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_Holo); // (for Android Built In Theme)

        setContentView(R.layout.activity_main);


        frname = findViewById(R.id.fname);
        lsname = findViewById(R.id.lname);
        save_bt = findViewById(R.id.bt_save);
        radioSex1 = findViewById(R.id.radioSex);
        radioFmale1 = findViewById(R.id.radioFmale);
        radioMale1 = findViewById(R.id.radioMale);
        imageView1 = findViewById(R.id.imageView);
        final String[] flag_image = {""};

        radioSex1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.radioFmale:
                        imageView1.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image[0] = "female";
                        break;

                    case R.id.radioMale:
                        imageView1.setImageDrawable(getDrawable(R.drawable.male));
                        flag_image[0] = "male";
                        break;

                }
            }
        }

        );

        save_bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if(frname.getText().toString().equals(""))
        {
            frname.setError("Hey, Please enter a value");
        }
        else if(lsname.getText().toString().equals(""))
        {
            lsname.setError("Hey, Please enter a value");
        }
        else if (radioSex1.getCheckedRadioButtonId() == -1)
        {
            radioFmale1.setError("Please select a gender");
            radioMale1.setError("Please select a gender");
        }
        else {

            Intent intentToProfile = new Intent(MainActivity.this, MyProfile.class);
            int selectedId = radioSex1.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) findViewById(selectedId);
            String gender = rb.getText().toString();
            Profile newProfile = new Profile(frname.getText().toString(), lsname.getText().toString(), gender);
            Bundle sendData = new Bundle();
            sendData.putSerializable(PROFILE, newProfile);
            intentToProfile.putExtra(TAG_IMAGE, sendData);
            startActivity(intentToProfile);
        }


    }
});




    }
}
