package com.ownproj.calculationintent;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculation extends AppCompatActivity {

    private TextView add;
    private EditText num1;
    private EditText num2;
    private Button bt_calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);


        num1 = findViewById(R.id.et_number1);
        num2 = findViewById(R.id.et_number2);
        bt_calculate = findViewById(R.id.bt_calculate);





        final Bundle bundle = getIntent().getExtras();
        String task = "None";
        if(bundle != null)
        {
            if(bundle.containsKey("ADD"))
            {
                 task = "Adding";

            }
        }

        final Intent intent = new Intent(this, MainActivity.class);

        bt_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n1 = num1.getText().toString();
                String n2 = num2.getText().toString();
                int num1 = Integer.parseInt(n1);
                int num2 = Integer.parseInt(n2);

                int addition = num1 + num2;

                Bundle b1 = new Bundle();
                b1.putInt("Result",addition);
                intent.putExtras(b1);
                startActivity(intent);
                finish();
            }
        });




    }
}
