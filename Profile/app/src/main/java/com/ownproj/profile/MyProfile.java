package com.ownproj.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

public class MyProfile extends AppCompatActivity {

    private ImageView imageViewProfile;
    private Button button_edit;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        imageViewProfile=findViewById(R.id.imageView_my_profile);
        button_edit=findViewById(R.id.bt_edit);

        final TextView tvFullName=(TextView)findViewById(R.id.textView_full_name);
        final TextView tvGender=(TextView)findViewById(R.id.textView_gender);
        Bundle extrasFromBundle=getIntent().getExtras().getBundle(MainActivity.TAG_IMAGE);
        Profile profile=(Profile)extrasFromBundle.getSerializable(MainActivity.PROFILE);
        final String[] flag_image = {""};

        if(profile!=null){
            if(profile.getGender().equals("Male")){
                imageViewProfile.setImageDrawable(getDrawable(R.drawable.male));
                String fullName= (profile.getFirstName()).concat("  "+profile.getLastName());

                tvFullName.setText(fullName);
                tvGender.setText(profile.getGender());
                flag_image[0] = "male";

            }
            else{

                imageViewProfile.setImageDrawable(getDrawable(R.drawable.female));
                String fullName= (profile.getFirstName()).concat("  "+profile.getLastName());
                tvFullName.setText(fullName);
                tvGender.setText(profile.getGender());
                flag_image[0] = "female";
            }

            button_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intentToEdit=new Intent(MyProfile.this,EditActivity.class);

                    Bundle extrasFromBundle=getIntent().getExtras().getBundle(MainActivity.TAG_IMAGE);
                    Profile profile=(Profile)extrasFromBundle.getSerializable(MainActivity.PROFILE);
                    Bundle newB=new Bundle();
                    newB.putSerializable("PROFILE",profile);
                    intentToEdit.putExtra("IMAGEFROMDISPLAYTOEDIT",extrasFromBundle.getBundle(MainActivity.TAG_IMAGE));
                    startActivity(intentToEdit);


                }
            });
        }



    }}
