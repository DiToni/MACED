package com.example.anton.ma_ced;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        Button sicherheitsebene2 = (Button)findViewById(R.id.sicherheitBtn);
        sicherheitsebene2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                Intent startIntent = new Intent(getApplicationContext(), SecurityScene.class);
                //show how to pass information to another activity
                startIntent.putExtra("com.example.anton.myfirstapp.SOMETHING", "Calculator");
                startActivity(startIntent);
                */
            }
        });

        Button sicherheitsebene = (Button)findViewById(R.id.sicherheitBtn);
        sicherheitsebene.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                Intent startIntent = new Intent(getApplicationContext(), SecurityScene.class);
                //show how to pass information to another activity
                startIntent.putExtra("com.example.anton.myfirstapp.SOMETHING", "Calculator");
                startActivity(startIntent);
                */
            }
        });
    }
}
