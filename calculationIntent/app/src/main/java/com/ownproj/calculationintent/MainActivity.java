package com.ownproj.calculationintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button bt_add;
    private Button bt_sub;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_add = findViewById(R.id.bt_add);
        bt_sub = findViewById(R.id.bt_subtract);
        tv_result = findViewById(R.id.tv_result);


        final Intent intent = new Intent(this, Calculation.class);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ADD", "Addition");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        bt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("SUB", "Subtraction");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ///Displaying the result

        Bundle bundle = getIntent().getExtras();
        int result = 0;
        if (bundle != null) {
            if (bundle.containsKey("Result")) {
                result = bundle.getInt("Result");
                Log.d("Add", "onCreate: " + result);
            }
        }
        tv_result.setText(String.valueOf(result));

    }
}
