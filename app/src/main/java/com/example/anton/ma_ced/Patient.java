package com.example.anton.ma_ced;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;


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

    private ArrayList<Stool> stoolList = new ArrayList<Stool>();
    private ArrayList<Pain> painList = new ArrayList<Pain>();
    private ArrayList<Symptom> symptomList = new ArrayList<Symptom>();


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

    public static void deserialisieren() {
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
