package com.example.anton.ma_ced;

import android.graphics.Color;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Patient {
    private String vorname;
    private String nachname;
    private LocalDate birthdate;
    private int height; // in cm
    private double weight; // in kg
    private int pin;
    private int password;
    private ArrayList<Stool> stoolList = new ArrayList<Stool>();
    private ArrayList<Pain> painList;
    private ArrayList<Symptom> symptomList;
    private List<Event> eventList; //as propertie?! observervableList
    Calendar currentCalendar;

    public Patient(){
        eventList = new ArrayList<>();//load with json
        currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"),Locale.GERMANY);

        addStoolEvent(2018, 06, 3, 10, 56, new Stool());
    }

    public ArrayList<Stool> getStoolList() {
        return stoolList;
    }

    public void setStoolList(ArrayList<Stool> stoolList) {
        this.stoolList = stoolList;
    }

    public ArrayList<Pain> getPainList() {
        return painList;
    }

    public void setPainList(ArrayList<Pain> painList) {
        this.painList = painList;
    }

    public ArrayList<Symptom> getSymptomList() {
        return symptomList;
    }

    public void setSymptomList(ArrayList<Symptom> symptomList) {
        this.symptomList = symptomList;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }


    public void addStool(Stool s){
        getStoolList().add(s);
    }
    public void addPain(Pain p){
        getPainList().add(p);
    }
    public void addSymptom(Symptom s){
        getSymptomList().add(s);
    }


    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    /**
     *
     * @param year
     * @param month 0 = january
     * @param day
     * @param hour
     * @param minute
     * @param stool
     */
    public void addStoolEvent(int year, int month, int day, int hour, int minute, Stool stool){
        int brownColor = Color.argb(255, 0, 0, 255);
        eventList.add(new Event(brownColor, computeTimeInMillis(year, month,  day, hour, minute), stool));
    }

    /**
     *
     * @param year
     * @param month 0 = january
     * @param day
     * @param hour
     * @param minute
     * @return timeInMillis
     */
    private long computeTimeInMillis(int year, int month, int day, int hour, int minute){
        currentCalendar.set(year, month, day, hour, minute);
        return currentCalendar.getTimeInMillis();
    }
}
