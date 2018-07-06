package com.example.anton.ma_ced;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        //todo: exchange thread with creating and loading patient
        Patient patient = Patient.instance();
        //deserialize

        Thread myThread = new Thread(){
            @Override
            public void run() {

                try {
                    sleep(3000);

                //    if (abfrage2 ) {

                        Intent intent = new Intent(getApplicationContext(), ScreenLogin.class);
                        startActivity(intent);
                  /*  } else {
                        Intent intent = new Intent(getApplicationContext(), CalendarFragment.class);
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
