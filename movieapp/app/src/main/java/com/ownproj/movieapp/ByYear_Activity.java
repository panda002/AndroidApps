package com.ownproj.movieapp;

import androidx.appcompat.app.AppCompatActivity;
//Sidharth and Nayana
// group Groups1 6
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ByYear_Activity extends AppCompatActivity {


        private TextView tv_title, tv_desc, tv_genre, tv_rating, tv_year, tv_imdb;
        private ImageView iv_prev;
        private ImageView  iv_next;
        private ImageView  iv_last;
        private ImageView   iv_first;
        private Button bt_finsih;
        ArrayList<Movies> mlist= new ArrayList<>();
        int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_year_);
        android:setTitle("Movies by Year");
            tv_title=findViewById(R.id.tv_title);
            tv_desc=findViewById(R.id.tv_description);
            tv_rating=findViewById(R.id.tv_rating);
            tv_year=findViewById(R.id.tv_year);
            tv_imdb=findViewById(R.id.tv_Imdb);
            iv_next=findViewById(R.id.iv_next);
            iv_last=findViewById(R.id.iv_last);
            tv_genre=findViewById(R.id.tv_genre);
            bt_finsih=findViewById(R.id.bt_finish);
            iv_first=findViewById(R.id.iv_first);
            iv_prev=findViewById(R.id.iv_prev);



            if (getIntent() !=null && getIntent().getExtras() !=null) {
                String action = getIntent().getAction();
                String type = getIntent().getType();


                mlist = getIntent().getExtras().getParcelableArrayList(MainActivity.KEY_YEAR);
                final int j = mlist.size();

                Collections.sort(mlist, new Comparator<Movies>() {
                    public int compare(Movies o1, Movies o2) {
                        return Integer.compare(o2.myear, o1.myear);
                    }

                });

                i=0;
                tv_title.setText(mlist.get(i).mname);
                tv_desc.setText(mlist.get(i).mdesc);
                tv_genre.setText(mlist.get(i).genre);
                tv_rating.setText(String.valueOf(mlist.get(i).seekbarprogress));
                tv_year.setText(String.valueOf(mlist.get(i).myear));
                tv_imdb.setText(mlist.get(i).mimdb);

                iv_first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i==0)
                        {
                            Toast.makeText(ByYear_Activity.this, "First movie", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            i = 0;

                            tv_title.setText(mlist.get(i).mname);
                            tv_desc.setText(mlist.get(i).mdesc);
                            tv_genre.setText(mlist.get(i).genre);
                            tv_rating.setText(String.valueOf(mlist.get(i).seekbarprogress));
                            tv_year.setText(String.valueOf(mlist.get(i).myear));
                            tv_imdb.setText(mlist.get(i).mimdb);
                        }

                    }
                });

                iv_prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i==0)
                        {
                            Toast.makeText(ByYear_Activity.this, "First movie", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            i--;
                            tv_title.setText(mlist.get(i).mname);
                            tv_desc.setText(mlist.get(i).mdesc);
                            tv_genre.setText(mlist.get(i).genre);
                            tv_rating.setText(String.valueOf(mlist.get(i).seekbarprogress));
                            tv_year.setText(String.valueOf(mlist.get(i).myear));
                            tv_imdb.setText(mlist.get(i).mimdb);

                        }

                    }
                });

                iv_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i==j-1)
                        {
                            Toast.makeText(ByYear_Activity.this, "Last movie", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            i++;

                            if(i<j) {
                                tv_title.setText(mlist.get(i).mname);
                                tv_desc.setText(mlist.get(i).mdesc);
                                tv_genre.setText(mlist.get(i).genre);
                                tv_rating.setText(String.valueOf(mlist.get(i).seekbarprogress));
                                tv_year.setText(String.valueOf(mlist.get(i).myear));
                                tv_imdb.setText(mlist.get(i).mimdb);
                            }
                            else{
                                Toast.makeText(ByYear_Activity.this, "Last movie", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

                iv_last.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i==j-1)
                        {
                            Toast.makeText(ByYear_Activity.this, "Last movie", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            i = j - 1;
                            tv_title.setText(mlist.get(i).mname);
                            tv_desc.setText(mlist.get(i).mdesc);
                            tv_genre.setText(mlist.get(i).genre);
                            tv_rating.setText(String.valueOf(mlist.get(i).seekbarprogress));
                            tv_year.setText(String.valueOf(mlist.get(i).myear));
                            tv_imdb.setText(mlist.get(i).mimdb);
                        }
                    }
                });

                bt_finsih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });




            }
        }
    }
