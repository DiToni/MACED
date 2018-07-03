package com.example.anton.ma_ced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;

public class ScreenLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void onClickAnmeldeButton(final View openView){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Patient.class, Patient.instance());
        gsonBuilder.registerTypeAdapter(Stool.class, new Stool());
        gsonBuilder.registerTypeAdapter(Symptom.class, new Symptom());
        gsonBuilder.registerTypeAdapter(Pain.class, new Pain());

        final Gson gson = gsonBuilder.create();
        String line = "";

        StringBuffer stringBuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.res);
        Intent intent = new Intent(getApplicationContext(), BurgerMenu.class);

        startActivity(intent);
    }
}
