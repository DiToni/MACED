package com.example.anton.ma_ced;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TimePicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timePicker);
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), StoolList.class);
        startActivity(intent);
    }

    public void onClickButtonFertig(final View openView){
        Intent intent = new Intent(getApplicationContext(), CreateStool.class);
        startActivity(intent);
    }
}
