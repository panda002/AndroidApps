package com.ownproj.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.*;

public class EditActivity extends AppCompatActivity {
    private EditText frname;
    private EditText lsname;
    private ImageView imageView;
    public static final String TAG_IMAGE = "flagimage";
    public static final String PROFILE="myProfile";
    private Button save_bt;
    private RadioGroup radioSex1;
    private RadioButton radioFmale1;
    private RadioButton radioMale1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        frname = findViewById(R.id.fname);
        lsname = findViewById(R.id.lname);

        save_bt = findViewById(R.id.bt_save);
        radioFmale1 = findViewById(R.id.radioFmale);
        radioMale1 = findViewById(R.id.radioMale);
        imageView = findViewById(R.id.imageView);
        Bundle extrasFromBundle = getIntent().getExtras().getBundle(MainActivity.TAG_IMAGE);
        Profile profile = (Profile) extrasFromBundle.getSerializable(MainActivity.PROFILE);

        Intent intent = getIntent();
        String str = intent.getStringExtra("IMAGEFROMDISPLAYTOEDIT");
        frname.setText(profile.getFirstName());
        lsname.setText(profile.getLastName());

        if (profile != null) {
            if (profile.getGender().equals("Male")) {
                imageView.setImageDrawable(getDrawable(R.drawable.male));
                radioMale1.setChecked(true);
            } else {
                imageView.setImageDrawable(getDrawable(R.drawable.female));
                radioFmale1.setChecked(true);
            }
            save_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intentToProfile=new Intent (EditActivity.this,MyProfile.class);
                    int selectedId=radioSex1.getCheckedRadioButtonId();
                    RadioButton rb=(RadioButton)findViewById(selectedId);
                    String gender=rb.getText().toString();
                    Profile newProfile= new Profile(frname.getText().toString(),lsname.getText().toString(),gender);
                    Bundle sendData=new Bundle();
                    sendData.putSerializable(PROFILE,newProfile);
                    intentToProfile.putExtra(TAG_IMAGE,sendData);
                    startActivity(intentToProfile);
                    finish();

                }
            });

        }
    }
}
