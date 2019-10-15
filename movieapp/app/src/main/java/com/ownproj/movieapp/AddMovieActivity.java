package com.ownproj.movieapp;

import androidx.appcompat.app.AppCompatActivity;
//Sidharth and Nayana
// group Groups1 6
import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {

    private String genre;
    private int seekbarprogress;
    private int ratingseekbarprogress;
    private String mname;
    private String mdesc;
    private Integer myear;
    private String mimdb;
    private TextView seekprogressnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_activuty);
        final EditText et_name = (EditText) findViewById(R.id.et_name);
        final EditText et_description = (EditText) findViewById(R.id.et_description);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final EditText et_year = (EditText) findViewById(R.id.et_year);
        final EditText et_imdb = (EditText) findViewById(R.id.et_imdb);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        Button bt_addmovie = (Button) findViewById(R.id.bt_addmovie);
        seekprogressnum = findViewById(R.id.seekprogress);

        Android:setTitle("Add Movie");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genre = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        seekBar.setProgress(0);
        seekBar.setMax(5);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ratingseekbarprogress = i;
                seekprogressnum.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bt_addmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().isEmpty())

                {
                    Toast.makeText(AddMovieActivity.this, "Please enter a movide name", Toast.LENGTH_SHORT).show();
                }
                else if(et_description.getText().toString().isEmpty())
                    {
                        Toast.makeText(AddMovieActivity.this,"PLease enter the description",Toast.LENGTH_SHORT).show();
                    }
                    else if(genre.equals("Select"))
                    {
                        Toast.makeText(AddMovieActivity.this,"Please enter a genre",Toast.LENGTH_SHORT).show();
                    }
                    else if ((et_year.getText().toString().isEmpty())||(et_year.getText().toString().length() < 4) ||(et_year.getText().toString().length()>4))
                {
                    Toast.makeText(AddMovieActivity.this, "Please enter a valid year", Toast.LENGTH_SHORT).show();
                }else if ((et_imdb.getText().toString().isEmpty())||(!(et_imdb.getText().toString().startsWith("https://www.imdb.com/")))) {
                    Toast.makeText(AddMovieActivity.this,"Please enter the IMDB link in the format https://www.imdb.com/",Toast.LENGTH_SHORT).show();
                }

                    else{
                    mname = et_name.getText().toString();
                    mdesc = et_description.getText().toString();
                    myear = Integer.parseInt(et_year.getText().toString());
                    mimdb = et_imdb.getText().toString();
                    Intent addIntent = new Intent();
                    addIntent.putExtra(MainActivity.KEY_ADD, new Movies(mname, mdesc, genre, ratingseekbarprogress, myear, mimdb));
                    setResult(RESULT_OK, addIntent);
                    finish();
                    Toast.makeText(AddMovieActivity.this, "Movie " + mname + " added", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}
