package com.example.anton.ma_ced;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;

public class Pain implements JsonSerializer<Pain>, JsonDeserializer<Pain> {
    private Calendar calendar;//date and time
    private int score;
    private String localization;
    private String type;
    private String period;
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


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public Pain deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String jsonTime = jsonObject.get("time").getAsString();
        final int jsonScore = jsonObject.get("score").getAsInt();
        final String jsonLocalization = jsonObject.get("localization").getAsString();
        final String jsonPeriod = jsonObject.get("period").getAsString();
        final boolean jsonIngestion = jsonObject.get("ingestion").getAsBoolean();
        final String jsonNotes = jsonObject.get("notes").getAsString();


        final Pain pain = new Pain();
        pain.setTime(jsonTime);
        pain.setScore(jsonScore);
        pain.setLocalization(jsonLocalization);
        pain.setPeriod(jsonPeriod);
        pain.setIngestion(jsonIngestion);
        pain.setNotes(jsonNotes);
        return pain;
    }

    @Override
    public JsonElement serialize(Pain src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("time", getTime());
        jsonObject.addProperty("score", getScore());
        jsonObject.addProperty("localization", getLocalization());
        jsonObject.addProperty("notes", getNotes());
        jsonObject.addProperty("period", getPeriod());
        jsonObject.addProperty("ingestion", isIngestion());
        return jsonObject;
    }
}
