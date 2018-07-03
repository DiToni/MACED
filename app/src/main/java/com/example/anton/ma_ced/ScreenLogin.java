package com.example.anton.ma_ced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

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

        final Gson gson = gsonBuilder.create();

        FileInputStream fis = null;
        try {
            fis = openFileInput("serialize.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            text = br.readLine();

            System.out.println("1" + text);

            Patient patient = gson.fromJson(text, Patient.class);

            System.out.println(patient);
            if (patient.getStoolList().isEmpty()) {
                System.out.println("Liste leer");
            } else {
                System.out.println("Liste voll");
            }


        } catch (FileNotFoundException e) {
            File file = new File("serialize.json");
            System.out.println("Json File erstellt");
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

            Intent intent = new Intent(getApplicationContext(), StoolList.class);

            startActivity(intent);
        }
    }
}
