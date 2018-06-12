package com.example.anton.ma_ced;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class stool_scale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stool_scale);
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), create_stool.class);
        startActivity(intent);
    }
}
