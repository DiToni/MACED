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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class Patient implements JsonSerializer<Patient>, JsonDeserializer<Patient>{
    @SerializedName("givenname")
    private String vorname;
    @SerializedName("surname")
    private String nachname;
    /*@SerializedName("birthdate")
    private LocalDate birthdate;*/
    @SerializedName("height")
    private int height; // in cm
    @SerializedName("weight")
    private double weight; // in kg
    @SerializedName("pin")
    private int pin;
    @SerializedName("password")
    private int password;
    private static Patient patient;

    private ArrayList<Stool> stoolList = new ArrayList<Stool>();
    private ArrayList<Pain> painList = new ArrayList<Pain>();
    private ArrayList<Symptom> symptomList = new ArrayList<Symptom>();
    private List<Event> eventList = new ArrayList<>(); //as propertie?! observervableList ->load with json
    Calendar currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"), Locale.GERMANY);

    public static Patient instance(){
        if (patient == null){
            patient = new Patient();
        }
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

   /* public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }*/

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

    public static void serialisieren(){

        Gson gson = new Gson();
        //instance().setBirthdate();
        instance().setNachname("Müller");
        instance().setVorname("Hans");
        instance().setHeight(123);
        instance().setWeight(123.5);
        instance().setPin(1234);
        instance().setPassword(1234);


        String json = gson.toJson(instance());
        System.out.println(json);
        /*try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(instance());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void deserialisieren() {//why static
        /*String json = "{'height':12, 'birthdate' = null}";
        Gson gson = new Gson();
        Patient patient = gson.
        System.out.println(json);*/
        /*try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            patient = (Patient) ois.readObject();

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    return patient;*/
    }


    @Override
    public JsonElement serialize(Patient src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("givenname", instance().getVorname());
        jsonObject.addProperty("surname", instance().getNachname());
        jsonObject.addProperty("height", instance().getHeight());
        jsonObject.addProperty("weight", instance().getWeight());
        jsonObject.addProperty("pin", instance().getPin());
        jsonObject.addProperty("password", instance().getPassword());

        final JsonElement jsonElementStool = context.serialize(instance().getStoolList());
        jsonObject.add("stool", jsonElementStool);

        final JsonElement jsonElementPain = context.serialize(instance().getPainList());
        jsonObject.add("pain", jsonElementPain);

        final JsonElement jsonElementSymptom = context.serialize(instance().getSymptomList());
        jsonObject.add("symptom", jsonElementSymptom);

        return jsonObject;
    }

    @Override
    public Patient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
       final JsonObject jsonObject = json.getAsJsonObject();

       final String jsonGivenname = jsonObject.get("givenname").getAsString();
       final String jsonSurname = jsonObject.get("surname").getAsString();
       final int jsonHeight = jsonObject.get("height").getAsInt();
       final int jsonWeight = jsonObject.get("weight").getAsInt();
       final int jsonPin = jsonObject.get("pin").getAsInt();
       final int jsonPassword = jsonObject.get("password").getAsInt();

       stoolList  = context.deserialize(jsonObject.get("stool"), Stool.class);
       painList = context.deserialize(jsonObject.get("pain"), Pain.class);
       symptomList = context.deserialize(jsonObject.get("symptom"), Symptom.class);

       instance().setWeight(jsonWeight);
       instance().setHeight(jsonHeight);
       instance().setVorname(jsonGivenname);
       instance().setNachname(jsonSurname);
       instance().setPin(jsonPin);
       instance().setPassword(jsonPassword);


        return instance();
    }
}
