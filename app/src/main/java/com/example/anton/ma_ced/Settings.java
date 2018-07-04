package com.example.anton.ma_ced;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        Button sicherheitsebene2 = (Button)findViewById(R.id.sicherheitBtn);


        Button sicherheitsebene = (Button)findViewById(R.id.sicherheitBtn);

    }
}
