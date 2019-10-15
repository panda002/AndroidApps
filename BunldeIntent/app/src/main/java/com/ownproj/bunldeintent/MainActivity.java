package com.ownproj.bunldeintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_fname;
    private EditText et_lname;
    private Button bt_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_fname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_lname);
        bt_send = findViewById(R.id.bt_send);

        final Intent intent = new Intent(this, Main2Activity.class);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("fname", et_fname.getText().toString());
                bundle.putString("lname", et_lname.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        Bundle bundle = getIntent().getExtras();


        }
}
