package com.ownproj.threaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService ThreadPool;
    Handler handler;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                Log.d("Demo", "handleMessage: "+message.what);
                return false;
            }
        });

        new Thread(new DoWork()).start();

        //Normal thread demo
//        ThreadPool = Executors.newFixedThreadPool(2);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Thread thread = new Thread(new DoWork(),"Worker 1");
//                //thread.start();
//                ThreadPool.execute(new DoWork());
//            }
//        });
    }

    class DoWork implements Runnable {

        public void run() {

            Log.d("Demo", "Started Work: ");
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
            for (int i = 0; i <= 100; i++) {
                for (int j = 0; j < 1000; j++) {
                }

                Message message = new Message();
                message.what = i;
                handler.sendMessage(message);

            }
            Log.d("Demo", "Ended Work: ");
        }
    }
}
