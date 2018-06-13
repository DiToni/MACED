package com.example.anton.ma_ced;

public class Pain {
    // TODO private DATENTYP date
    // TODO private DATENTYP time
    private int score;
    private String localization;
    private String period; // in minutes
    private boolean ingestion;  //Nahrungsaufnahme
    private String notes;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isIngestion() {
        return ingestion;
    }

    public void setIngestion(boolean ingestion) {
        this.ingestion = ingestion;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }





}
