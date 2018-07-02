package com.example.anton.ma_ced;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class SplashScreen extends AppCompatActivity {
    //SecurityScene s2 = new SecurityScene();
    //public boolean abfrage = true;
    //Switch switch2 = (Switch) s2.findViewById(R.id.switch2);
    //private boolean abfrage2;
/*
    public SplashScreen() {
        abfrage2 = switch2.getShowText();
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread(){
            @Override
            public void run() {

                try {
                    sleep(3000);

                //    if (abfrage2 ) {

                        Intent intent = new Intent(getApplicationContext(), ScreenLogin.class);
                        startActivity(intent);
                  /*  } else {
                        Intent intent = new Intent(getApplicationContext(), Calendar.class);
                        startActivity(intent);
                    }
                    */
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
