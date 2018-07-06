package com.example.anton.ma_ced;

import android.graphics.Color;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class Patient implements JsonSerializer<Patient>, JsonDeserializer<Patient>{
    /*@SerializedName("givenname")
    private String vorname;
    @SerializedName("surname")
    private String nachname;
    @SerializedName("birthdate")
    private LocalDate birthdate;
    @SerializedName("height")
    private int height; // in cm
    @SerializedName("weight")
    private double weight; // in kg
    @SerializedName("pin")
    private int pin;
    @SerializedName("password")
    private String password;*/
    private static Patient patient;

    private ArrayList<Stool> stoolList = new ArrayList<Stool>();
    private ArrayList<Pain> painList = new ArrayList<Pain>();
    private ArrayList<Symptom> symptomList = new ArrayList<Symptom>();
    private List<Event> eventList = new ArrayList<>();
    private Calendar currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"), Locale.GERMANY);

    public static Patient instance(){
        if (patient == null){
            patient = new Patient();
        }
        return patient;
    }

    public static Patient instance(Patient patient){
        Patient.patient = patient;
        return patient;
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

    /*public String getVorname() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/


    public void addStool(Stool s){
        getStoolList().add(s);
        addStoolEvent(s);
    }
    public void addPain(Pain p){
        getPainList().add(p);
        addPainEvent(p);
    }
    public void addSymptom(Symptom s){
        getSymptomList().add(s);
        addSymptomEvent(s);
    }

    public Calendar getCurrentCalendar() {
        return currentCalendar;
    }

    public void setCurrentCalendar(Calendar currentCalendar) {
        this.currentCalendar = currentCalendar;
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
    public void addStoolEvent(Stool stool){//todo: by adding a new event, add event to compactcalenderview
        int blueColor = Color.argb(255, 0, 0, 255);
        Event stoolEvent = new Event(blueColor, computeTimeInMillis(stool.getCalendar()));
        if(!eventList.contains(stoolEvent)) {
            eventList.add(stoolEvent);
        }
    }

    /**
     *
     * @param pain
     */
    public void addPainEvent(Pain pain){
        int greenColor = Color.argb(255, 0, 255, 0);
        Event painEvent = new Event(greenColor, computeTimeInMillis(pain.getCalendar()));
        if(!eventList.contains(painEvent)) {
            eventList.add(painEvent);
        }
    }

    /**
     *
     * @param symptom
     */
    public void addSymptomEvent(Symptom symptom){
        int blackColor = Color.argb(255, 0, 0, 0);
        Event symptomEvent = new Event(blackColor, computeTimeInMillis(symptom.getCalendar()));
        if(!eventList.contains(symptomEvent)) {
            eventList.add(symptomEvent);
        }
    }

    /**
     * january = 0
     * @param calendar
     * @return
     */
    private long computeTimeInMillis(Calendar calendar){
        Calendar currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"), Locale.GERMANY);
        currentCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),  calendar.get(Calendar.DAY_OF_MONTH));
        return getCurrentCalendar().getTimeInMillis();
    }




    @Override
    public JsonElement serialize(Patient src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        /*jsonObject.addProperty("givenname", instance().getVorname());
        jsonObject.addProperty("surname", instance().getNachname());
        jsonObject.addProperty("height", instance().getHeight());
        jsonObject.addProperty("weight", instance().getWeight());
        jsonObject.addProperty("pin", instance().getPin());
        jsonObject.addProperty("password", instance().getPassword());*/

        final JsonElement jsonElementStool = context.serialize(instance().getStoolList());
        jsonObject.add("stool", jsonElementStool);

        final JsonElement jsonElementPain = context.serialize(instance().getPainList());
        jsonObject.add("pain", jsonElementPain);

        final JsonElement jsonElementSymptom = context.serialize(instance().getSymptomList());
        jsonObject.add("symptom", jsonElementSymptom);

        final JsonElement jsonElementEvent = context.serialize(instance().getEventList());
        jsonObject.add("event", jsonElementEvent);

        return jsonObject;
    }

    @Override
    public Patient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
       final JsonObject jsonObject = json.getAsJsonObject();

       /*final String jsonGivenname = jsonObject.get("givenname").getAsString();
       final String jsonSurname = jsonObject.get("surname").getAsString();*/
       final int jsonHeight = jsonObject.get("height").getAsInt();
       final int jsonWeight = jsonObject.get("weight").getAsInt();
       final int jsonPin = jsonObject.get("pin").getAsInt();
       final String jsonPassword = jsonObject.get("password").getAsString();

       stoolList  = context.deserialize(jsonObject.get("stool"), stoolList.getClass());
       painList = context.deserialize(jsonObject.get("pain"), painList.getClass());
       symptomList = context.deserialize(jsonObject.get("symptom"), symptomList.getClass());

       /*instance().setWeight(jsonWeight);
       instance().setHeight(jsonHeight);
       instance().setVorname(jsonGivenname);
       instance().setNachname(jsonSurname);
       instance().setPin(jsonPin);
       instance().setPassword(jsonPassword);*/

        return instance();
    }
}
