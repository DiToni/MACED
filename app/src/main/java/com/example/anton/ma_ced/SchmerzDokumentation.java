package com.example.anton.ma_ced;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.TimePicker;

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

    //Schmerz erstellen
    private Button ok;

    //TimePicker
    private EditText time;
    java.util.Calendar mcurrentTime = java.util.Calendar.getInstance();
    private int hour = mcurrentTime.get(java.util.Calendar.HOUR_OF_DAY);
    private int minute = mcurrentTime.get(Calendar.MINUTE);
    DecimalFormat df= new DecimalFormat("00");

    //Nahrungsaufnahme
    private CheckBox checkBox2;

    //Notizen zur Nahrungsaufnahme
    private TextInputEditText nahrung;

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
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SchmerzDokumentation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(df.format(selectedHour) + ":" + df.format(selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Uhrzeit auswählen");
                mTimePicker.show();

            }
        });

        checkBox2= (CheckBox)findViewById(R.id.checkBox2);
        nahrung= (TextInputEditText)findViewById(R.id.nahrung);
        ok=(Button)findViewById(R.id.buttonOK);
        ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View w){
                Pain p= new Pain();
                p.setScore(Integer.parseInt(skala.getText().toString()));
                if(spinnerLokalisation!=null){
                    p.setLocalization(spinnerLokalisation.getSelectedItem().toString());
                }

                if(spinnerArt!=null){
                    p.setType(spinnerArt.getSelectedItem().toString());
                }

                if(spinnerZeitraum!=null){
                    p.setPeriod(spinnerZeitraum.getSelectedItem().toString());
                }

                p.setIngestion(checkBox2.isActivated());
                if(checkBox2.isActivated() && nahrung.getText()!=null){
                    p.setNotes(nahrung.getText().toString());
                }else{
                    p.setNotes("");
                }

                p.setTime(time.getText().toString());

                //NOCH HINZUFUEGEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        });

    }
}
