package com.example.anton.ma_ced;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

public class SchmerzDokumentation extends AppCompatActivity {
    //Schmerzskala
    private SeekBar seekBar;
    private TextView skala; //müssen eig alle TextViews deklariert werden??

    //Lokalisation
    private Spinner spinnerLokalisation;

    //Art des Schmerzes
    private Spinner spinnerArt;

    //Schmerzzeitraum
    private Spinner spinnerZeitraum;

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

    //Checkbox
    private CheckBox checkBox;

    //Nahrungsaufnahme
    private CheckBox checkBox2;

    //Notizen zur Nahrungsaufnahme
    private TextInputLayout nahrung;

    //Schmerz erstellen
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schmerz_dokumentation);

        //Schmerzskala SeekBar
        seekBar=findViewById(R.id.seekBar1);
        skala=findViewById(R.id.textView16);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //für was ist "@SuppressLint("SetTextI18n")"??
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    switch(seekBar.getProgress()){
                        case 0:
                            skala.setText("keine");
                            break;
                        case 1:
                            skala.setText("schwach");
                            break;
                        case 2:
                            skala.setText("mittel");
                            break;
                        case 3:
                            skala.setText("stark");
                            break;
                        case 4:
                            skala.setText("sehr stark");
                            break;
                    }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Lokalisation Spinner hinzufuegen
        spinnerLokalisation= findViewById(R.id.spinnerLokalisation);
        ArrayAdapter<CharSequence> adapterLokalisation= ArrayAdapter.createFromResource(this, R.array.lokalisationen, android.R.layout.simple_spinner_item);
        adapterLokalisation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLokalisation.setAdapter(adapterLokalisation);

        //Art des Schmerzes hinzufügen
        spinnerArt=(Spinner)findViewById(R.id.spinnerArt);
        ArrayAdapter<CharSequence> adapterSchmerzart= ArrayAdapter.createFromResource(this, R.array.schmerzart, android.R.layout.simple_spinner_item);
        adapterSchmerzart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArt.setAdapter(adapterSchmerzart);

        //Schmerzzeitraum hinzufuegen
        spinnerZeitraum=(Spinner)findViewById(R.id.spinnerZeitraum);
        ArrayAdapter<CharSequence> adapterZeitraum= ArrayAdapter.createFromResource(this, R.array.zeitraum, android.R.layout.simple_spinner_item);
        adapterZeitraum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZeitraum.setAdapter(adapterZeitraum);

        //TimePicker
        time = (EditText) findViewById(R.id.schmerztime);
        time.setText(df.format(hour) + ":" + df.format(minute));
        zeit.set(year, month, date, hour, minute);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SchmerzDokumentation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(df.format(selectedHour) + ":" + df.format(selectedMinute));
                        zeit.set(year, month, date, selectedHour, selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Uhrzeit auswählen");
                mTimePicker.show();

            }
        });

        //Checkbox
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        nahrung= (TextInputLayout)findViewById(R.id.nahrung);
        ok=(Button)findViewById(R.id.buttonOK);



    }

    public void onClickButtonWeiter(final View openView){

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Patient.class, Patient.instance());

        final Gson gson = gsonBuilder.create();

        Pain p= new Pain();
        //score ist int
        p.setScore(seekBar.getProgress());
        if(spinnerLokalisation.getSelectedItem()!=null){
            p.setLocalization(spinnerLokalisation.getSelectedItem().toString());
        }

        if(spinnerArt.getSelectedItem()!=null){
            p.setType(spinnerArt.getSelectedItem().toString());
        }

        if(spinnerZeitraum.getSelectedItem()!=null){
            p.setPeriod(spinnerZeitraum.getSelectedItem().toString());
        }

        p.setIngestion(checkBox2.isChecked());
        if(checkBox2.isChecked() && nahrung.getEditText()!=null){
            p.setNotes(nahrung.getEditText().toString());
        }else{
            p.setNotes("");
        }

        p.setCalendar(zeit);

        Patient.instance().addPain(p);

        final String json = gson.toJson(Patient.instance());
        System.out.println(json);

        FileOutputStream fos = null;

        try {
            fos = openFileOutput("serialize.json", MODE_PRIVATE);
            fos.write(json.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "serialize.txt",
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
