package com.example.anton.ma_ced;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

public class SymptomeDokumentation extends AppCompatActivity {
    //Symptom auswählen
    private Spinner spinnerSymptom;

    //Zeitraum festlegen
    private Spinner spinnerSyZeitraum;

    //TimePicker
    private EditText time;
    Calendar mcurrentTime = Calendar.getInstance();
    private int hour = mcurrentTime.get(java.util.Calendar.HOUR_OF_DAY);
    private int minute = mcurrentTime.get(Calendar.MINUTE);
    private int year= Patient.instance().getCurrentCalendar().get(Calendar.YEAR);
    private int month=Patient.instance().getCurrentCalendar().get(Calendar.MONTH);
    private int date= Patient.instance().getCurrentCalendar().get(Calendar.DATE);
    DecimalFormat df= new DecimalFormat("00");

    java.util.Calendar zeit=  java.util.Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptome_dokumentation);

        //Symptom Spinner hinzufuegen
        spinnerSymptom= (Spinner)findViewById(R.id.spinnerSymptom);
        ArrayAdapter<CharSequence> adapterSymptom= ArrayAdapter.createFromResource(this, R.array.symptome, android.R.layout.simple_spinner_item);
        adapterSymptom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSymptom.setAdapter(adapterSymptom);


        //Zeitraum Spinner hinzufuegen
        spinnerSyZeitraum= (Spinner)findViewById(R.id.spinnerSyZeitraum);
        ArrayAdapter<CharSequence> adapterSyZeitraum= ArrayAdapter.createFromResource(this, R.array.zeitraum, android.R.layout.simple_spinner_item);
        adapterSyZeitraum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSyZeitraum.setAdapter(adapterSyZeitraum);

        //TimePicker
        time = (EditText) findViewById(R.id.symptomtime);
        time.setText(df.format(hour) + ":" + df.format(minute));
        zeit.set(year, month, date, hour, minute);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SymptomeDokumentation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(df.format(selectedHour) + ":" + df.format(selectedMinute));
                        zeit.set(year, month, date, selectedHour, selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Uhrzeit auswählen");
                mTimePicker.show();
            }
        });
    }

    public void onClickButtonCamera (final View openView){
        capturePhoto();
    }

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(intent, 1);
        }
    }

    public void onClickButtonWeiter (final View onView){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Patient.class, Patient.instance());

        final Gson gson = gsonBuilder.create();

        Symptom symptom = new Symptom();

        symptom.setCalendar(zeit);
        if(spinnerSymptom.getSelectedItem()!=null){
            symptom.setSymptom(spinnerSymptom.getSelectedItem().toString());
        }
        symptom.setPeriod(spinnerSyZeitraum.getSelectedItem().toString());
        symptom.setSymptom(spinnerSymptom.getSelectedItem().toString());

        Patient.instance().addSymptom(symptom);

        final String json = gson.toJson(Patient.instance());
        System.out.println(json);

        FileOutputStream fos = null;

        try {
            fos = openFileOutput("serialize.json", MODE_PRIVATE);
            fos.write(json.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "serialize.json",
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Intent intent = new Intent(getApplicationContext(), StoolList.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);//FLAG_ACTIVITY_NEW_TASK: if used to start the root activity of a task, it will bring any currently running instance of that task to the foreground, and then clear it to its root state.
        startActivity(intent);
        finish();
    }
}
