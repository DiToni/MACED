package com.example.anton.ma_ced;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Symptom implements JsonSerializer<Symptom>, JsonDeserializer<Symptom>{
    //TODO private DATENTYP date
    private String time;
    //TODO private DATENTYP picture
    private String symptom;
    private String period; // in minutes
    //private String note;

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    /*public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }*/

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public Symptom deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final String jsonTime = jsonObject.get("time").getAsString();
        final String jsonSymptom = jsonObject.get("symptom").getAsString();
        final String jsonPeriod = jsonObject.get("period").getAsString();
        //final String jsonNote = jsonObject.get("note").getAsString();


        final Symptom symptom = new Symptom();
        symptom.setSymptom(jsonSymptom);
        //symptom.setNote(jsonNote);
        symptom.setTime(jsonTime);
        symptom.setPeriod(jsonPeriod);
        return symptom;
    }

    @Override
    public JsonElement serialize(Symptom src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("time", getTime());
        jsonObject.addProperty("period", getPeriod());
        jsonObject.addProperty("symptom", getSymptom());
        //jsonObject.addProperty("note", getNote());

        return jsonObject;
    }
}
