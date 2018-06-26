package com.example.anton.ma_ced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SymptomeDokumentation extends AppCompatActivity {
    //Symptom auswählen
    private Spinner spinnerSymptom;

    //Zeitraum festlegen
    private Spinner spinnerSyZeitraum;

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
    }
}
