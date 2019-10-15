package com.ownproj.bunldeintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    private Button bt_received;
    private TextView fname;
    private TextView lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        bt_received = findViewById(R.id.button);
        fname = findViewById(R.id.tv_fname);
        lname = findViewById(R.id.tv_lname);

        String fnameReceived = "None";

        if (bundle != null){
            if (bundle.containsKey("fname")){
                fnameReceived = bundle.getString("fname");
            }
        }


        //Log.d("NAME", "onCreate: " + fnameReceived);
        fname.setText(fnameReceived);

        final Intent intent = new Intent(this, MainActivity.class);
        bt_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();
                intent.putExtra("fname", String.valueOf(fname));
                intent.putExtra("fname", String.valueOf(lname));
                startActivity(intent);

            }
        });
        }
}
