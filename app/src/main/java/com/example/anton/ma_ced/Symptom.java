package com.example.anton.ma_ced;

public class Symptom {
    //TODO private DATENTYP date
    //TODO private DATENTYP time
    //TODO private DATENTYP picture
    private String symptom;
    private int period; // in minutes
    private String note;

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
