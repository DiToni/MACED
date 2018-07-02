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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.util.Calendar;

public class SymptomeDokumentation extends AppCompatActivity {
    //Symptom auswählen
    private Spinner spinnerSymptom;

    //Zeitraum festlegen
    private Spinner spinnerSyZeitraum;

    //TimePicker
    private EditText time;
    java.util.Calendar mcurrentTime = java.util.Calendar.getInstance();
    private int hour = mcurrentTime.get(java.util.Calendar.HOUR_OF_DAY);
    private int minute = mcurrentTime.get(Calendar.MINUTE);
    DecimalFormat df= new DecimalFormat("00");
    
    



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
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SymptomeDokumentation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(df.format(selectedHour) + ":" + df.format(selectedMinute));
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
        symptom.setTime(time.getText().toString());
        //symptom.setNote();
        symptom.setPeriod(spinnerSyZeitraum.getSelectedItem().toString());
        symptom.setSymptom(spinnerSymptom.getSelectedItem().toString());

        Patient.instance().addSymptom(symptom);

        final String json = gson.toJson(Patient.instance());
        System.out.println(json);



        Intent intent = new Intent(getApplicationContext(), StoolList.class);
        startActivity(intent);
    }
}
