package com.ownproj.pizzastore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button bt_clear;
    private Button bt_addtopping;
    private Button bt_checkout;
    private CheckBox cb_delivery;
    //private GridView gridView;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    ProgressBar progressBar;
    String[] topping = {"Bacon", "Cheese", "Garlic", "Green Pepper", "Mushroom", "Olives", "Onions", "Red Pepper"};
    LinearLayout topfirst, topsecond;
    ArrayList<Integer> toppingimage = new ArrayList<Integer>
            (Arrays.asList(R.drawable.bacon,R.drawable.cheese,R.drawable.garlic,
                    R.drawable.green_pepper,R.drawable.mashroom,R.drawable.olive,
                    R.drawable.onion,R.drawable.red_pepper));
    List<String> selectedtoppings = new ArrayList<String>();
    LinearLayout.LayoutParams params;
    int toppingnum = 0;

    //"Bacon","Cheese","Garlic","Green Pepper","Mushroom"
            //,"Olives","Onions", "Red Pepper"

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_clear = findViewById(R.id.bt_clearpizza);
        bt_addtopping = findViewById(R.id.bt_addtopping);
        bt_checkout = findViewById(R.id.bt_checkout);
        cb_delivery = findViewById(R.id.ck_delivery);
        topfirst = findViewById(R.id.topfirst);
        topsecond = findViewById(R.id.topsecond);
        progressBar = findViewById(R.id.progressBar);

        final float scale = getResources().getDisplayMetrics().density;
        params = new LinearLayout.LayoutParams((int)(60*scale), (int)(60*scale));

        builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Choose a Topping");
        builder.setItems(topping, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Log.d("Demo", "onClick: ");
                params.weight = 0.0f;
                ImageView selectedtopimage = new ImageView(MainActivity.this);
                progressBar.setProgress(++toppingnum,true);
                selectedtopimage.setLayoutParams(params);
                selectedtopimage.setImageDrawable(getDrawable(toppingimage.get(i)));
                selectedtoppings.add(topping[i]);
                selectedtopimage.setTag(topping[i]);
                selectedtopimage.setOnClickListener(MainActivity.this);

                if(toppingnum <= 5) {
                    topfirst.addView(selectedtopimage);
                }else if(toppingnum <= 10){
                    topsecond.addView(selectedtopimage);
                }
                if(toppingnum >= 10) {
                    Toast toast = Toast.makeText(MainActivity.this, "Maximum capacity!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toppingnum = 0;
                topfirst.removeAllViews();
                topsecond.removeAllViews();
            }
        });

        dialog = builder.create();
        bt_addtopping.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(toppingnum >= 10) {
                    Toast toast = Toast.makeText(MainActivity.this, "Maximum Topping capacity reached!", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    dialog.show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        LinearLayout currentSelectedLayout = findViewById(((View) v.getParent()).getId());
        currentSelectedLayout.removeView(v);
        if(toppingnum>=10)
        {
            toppingnum-=1;
        }
        else
        {
            toppingnum=9;
        }
        progressBar.setProgress(toppingnum);
        selectedtoppings.remove(v.getTag());
        System.out.println(selectedtoppings);
        if(currentSelectedLayout == topfirst && topsecond.getChildCount() != 0) {
            ImageView view1 = (ImageView) topsecond.getChildAt(0);
            topfirst.removeView(view1);
            topsecond.addView(view1);
        }

    }

}
