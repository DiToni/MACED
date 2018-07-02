package com.example.anton.ma_ced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ScreenLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void onClickAnmeldeButton(final View openView){
        Intent intent = new Intent(getApplicationContext(), StoolList.class);

        startActivity(intent);
    }
}
