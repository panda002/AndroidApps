package com.ownproj.movieapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
//Sidharth and Nayana
// group Groups1 6
public class MainActivity extends AppCompatActivity {


    private Button btadd;
    private Button btedit;
    private Button btdelete;
    private Button btlistyear;
    private Button btrating;
    int moview_details = 007;
    static String KEY_ADD="add";
    static String KEY_EDIT="edit";
    static int EDIT_REQUEST =10;
    public ArrayList<Movies> movielist=new ArrayList<>();
    int i,j;
    static String KEY_YEAR="year";
    static String KEY_RATING="rating";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {

            if(requestCode==moview_details)
            {
                if(resultCode == RESULT_OK)
                {
                    if (data != null && data.getExtras() != null)
                    {
                        Movies movies= data.getExtras().getParcelable(KEY_ADD);
                        movielist.add(movies);
                    }
                }
                else if(requestCode==RESULT_CANCELED)
                {}
            }
            if(requestCode==EDIT_REQUEST)
            {
                if(resultCode==RESULT_OK)
                {
                    Movies movies= data.getExtras().getParcelable(KEY_EDIT);
                    movielist.set(j, movies);
                }
                else if(requestCode==RESULT_CANCELED)
                {}
            }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btadd = findViewById(R.id.bt_add);
        btedit = findViewById(R.id.bt_edit);
        btdelete = findViewById(R.id.bt_delete);
        btlistyear = findViewById(R.id.bt_listyear);
        btrating = findViewById(R.id.bt_rating);

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addintent = new Intent(MainActivity.this, AddMovieActivity.class);

                startActivityForResult(addintent, moview_details);
            }
        });

        btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movielist.size() == 0) {
                    Toast.makeText(MainActivity.this, "No movies added yet", Toast.LENGTH_SHORT).show();
                } else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie");
                    final String[] arr1 = new String[movielist.size()];
                    for (int i = 0; i < movielist.size(); i++)
                    {
                        arr1[i] = movielist.get(i).mname;
                    }
                    builder.setItems(arr1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (j = 0; j < movielist.size(); j++) {
                                if (arr1[which].equals(movielist.get(j).mname)) {
                                    break;
                                }
                            }
                            Intent EditmovieIntent = new Intent(MainActivity.this, EditMovie.class);
                            EditmovieIntent.putExtra(KEY_EDIT, movielist.get(j));
                            startActivityForResult(EditmovieIntent, EDIT_REQUEST);

                        }
                    });
                    final AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movielist.size() == 0) {
                    Toast.makeText(MainActivity.this, "Please add a movie to delete", Toast.LENGTH_SHORT).show();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie");
                    final String[] arr = new String[movielist.size()];
                    for (int i = 0; i < movielist.size(); i++)
                    {
                        arr[i] = movielist.get(i).mname;
                    }
                    builder.setItems(arr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (j = 0; j < movielist.size(); j++) {
                                if (arr[which].equals(movielist.get(j).mname)) {
                                    break;
                                }
                            }
                            Toast.makeText(MainActivity.this, "Movie " + movielist.get(j).mname + "Deleted", Toast.LENGTH_SHORT).show();
                            movielist.remove(movielist.get(j));
                        }
                    });
                    final AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        btlistyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(movielist.size()==0)
                {
                    Toast.makeText(MainActivity.this, "Please add movie to see", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent year = new Intent(MainActivity.this, ByYear_Activity.class);
                    year.addCategory(Intent.CATEGORY_DEFAULT);
                    year.putParcelableArrayListExtra(KEY_YEAR, movielist);
                    startActivity(year);
            }

            }
        });


        btrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movielist.size()==0)
                {
                    Toast.makeText(MainActivity.this, "Please add movie to see", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent rating = new Intent(MainActivity.this, ByRating_Activity.class);
                    rating.addCategory(Intent.CATEGORY_DEFAULT);
                    rating.putParcelableArrayListExtra(KEY_RATING, movielist);
                    startActivity(rating);
                }
            }
        });






    }
}
