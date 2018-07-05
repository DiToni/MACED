package com.example.anton.ma_ced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScreenLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void onClickAnmeldeButton(final View openView) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Patient.class, Patient.instance());
        gsonBuilder.registerTypeAdapter(Stool.class, new Stool());
        gsonBuilder.registerTypeAdapter(Symptom.class, new Symptom());
        gsonBuilder.registerTypeAdapter(Pain.class, new Pain());
        gsonBuilder.registerTypeAdapter(Event.class, new EventSerialiser());

        final Gson gson = gsonBuilder.create();

        //try(Reader reader = new InputStreamReader(new FileInputStream(new File("serialize.json")),"UTF-8")){

        //}

        File file = new File("serialize.json");

        FileInputStream fis = null;
        try {
            //if(!file.exists())
            //    file.createNewFile();//creates file if not exists
            fis = openFileInput("serialize.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            text = br.readLine();

            System.out.println("1" + text);//test

            Patient patient = gson.fromJson(text, Patient.class);

          //test
            System.out.println(patient);

            //todo:test
            String testString = "test:";
            for (Stool e: Patient.instance().getStoolList()             ) {
                testString += e.toString();
            }
            System.out.println(testString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);

            startActivity(intent);
        }

    }
}
