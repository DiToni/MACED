package com.example.anton.ma_ced;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Spinner;

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
        spinnerArt.setAdapter(adapterZeitraum);
    }
}
