package com.ownproj.movieapp;

import androidx.appcompat.app.AppCompatActivity;
//Sidharth and Nayana
// group Groups1 6
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditMovie extends AppCompatActivity {
    private String genre;
    private int seekbarprogress;
    private int ratingseekbarprogress;
    private String mname;
    private String mdesc;
    private Integer myear;
    private String mimdb;
    private TextView seekprogress;
    private EditText et_name;
    private EditText et_description;
    private Spinner  spinner;
    private EditText et_year;
    private EditText et_imdb;
    private SeekBar  seekBar;
    private Button   bt_editmovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        android:
        setTitle("Edit Movie");
         et_name = findViewById(R.id.et_name);
         et_description= findViewById(R.id.et_description);
         spinner = findViewById(R.id.spinner);
         et_year= findViewById(R.id.et_year);
         et_imdb= findViewById(R.id.et_imdb);
         seekBar= findViewById(R.id.seekBar);
         bt_editmovie= findViewById(R.id.bt_addmovie);
        seekprogress = findViewById(R.id.seekprogress);

        if (getIntent() != null && getIntent().getExtras() != null) {
            Movies movie = getIntent().getExtras().getParcelable(MainActivity.KEY_EDIT);
            et_name.setText(movie.mname);
            et_description.setText(movie.mdesc);
            seekBar.setProgress(movie.seekbarprogress);
            seekprogress.setText(String.valueOf(movie.seekbarprogress));
            et_year.setText(String.valueOf(movie.myear));
            et_imdb.setText(movie.mimdb);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
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

            switch (movie.genre) {
                case "Select":
                    spinner.setSelection(0);
                    break;
                case "Action":
                    spinner.setSelection(1);
                    break;
                case "Animation":
                    spinner.setSelection(2);
                    break;
                case "Comedy":
                    spinner.setSelection(3);
                    break;
                case "Documentary":
                    spinner.setSelection(4);
                    break;
                case "Family":
                    spinner.setSelection(5);
                    break;
                case "Horror":
                    spinner.setSelection(6);
                    break;
                case "Crime":
                    spinner.setSelection(7);
                    break;
                case "Others":
                    spinner.setSelection(8);
                    break;
            }

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    ratingseekbarprogress = i;
                    seekprogress.setText(String.valueOf(i));
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            bt_editmovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (et_name.getText().toString().isEmpty()) {
                        Toast.makeText(EditMovie.this, "Please enter a movide name", Toast.LENGTH_SHORT).show();
                    } else if (et_description.getText().toString().isEmpty()) {
                        Toast.makeText(EditMovie.this, "PLease enter the description", Toast.LENGTH_SHORT).show();
                    } else if (genre.equals("Select")) {
                        Toast.makeText(EditMovie.this, "Please enter a genre", Toast.LENGTH_SHORT).show();
                    } else if ((et_year.getText().toString().isEmpty()) || (et_year.getText().toString().length() < 4) || (et_year.getText().toString().length() > 4)) {
                        Toast.makeText(EditMovie.this, "Please enter a valid year", Toast.LENGTH_SHORT).show();
                    } else if ((et_imdb.getText().toString().isEmpty()) || (!(et_imdb.getText().toString().startsWith("https://www.imdb.com/")))) {
                        Toast.makeText(EditMovie.this, "Please enter the IMDB link in the format https://www.imdb.com/", Toast.LENGTH_SHORT).show();
                    } else {
                        mname = et_name.getText().toString();
                        mdesc = et_description.getText().toString();
                        myear = Integer.parseInt(et_year.getText().toString());
                        mimdb = et_imdb.getText().toString();
                        Intent addIntent = new Intent();
                        addIntent.putExtra(MainActivity.KEY_ADD, new Movies(mname, mdesc, genre, seekbarprogress, myear, mimdb));
                        setResult(RESULT_OK, addIntent);
                        finish();
                        Toast.makeText(EditMovie.this, "Movie " + mname + " Edited", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }
    }
}
