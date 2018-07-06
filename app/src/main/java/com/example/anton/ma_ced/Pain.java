package com.example.anton.ma_ced;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Calendar;

public class Pain implements JsonSerializer<Pain>, JsonDeserializer<Pain> {
    private Calendar calendar;//date and time
    private int score;
    private String localization;
    private String period;
    private boolean ingestion;  //Nahrungsaufnahme
    private String notes;
    private String type;    //Art der Schmerzes

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

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public JsonElement serialize(Pain src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        final JsonElement calendar = context.serialize(getCalendar());
        jsonObject.add("calendar", calendar);

        jsonObject.addProperty("score", getScore());
        jsonObject.addProperty("localization", getLocalization());
        jsonObject.addProperty("notes", getNotes());
        jsonObject.addProperty("period", getPeriod());
        jsonObject.addProperty("ingestion", isIngestion());
        jsonObject.addProperty("type", getType());

        return jsonObject;
    }

    @Override
    public Pain deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        calendar  = context.deserialize(jsonObject.get("calendar"), Calendar.class);
        final int jsonScore = jsonObject.get("score").getAsInt();
        final String jsonLocalization = jsonObject.get("localization").getAsString();
        final String jsonPeriod = jsonObject.get("period").getAsString();
        final boolean jsonIngestion = jsonObject.get("ingestion").getAsBoolean();
        final String jsonNotes = jsonObject.get("notes").getAsString();
        final String jsonType=jsonObject.get("type").getAsString();


        final Pain pain = new Pain();
        pain.setCalendar(calendar);
        pain.setScore(jsonScore);
        pain.setLocalization(jsonLocalization);
        pain.setPeriod(jsonPeriod);
        pain.setIngestion(jsonIngestion);
        pain.setNotes(jsonNotes);
        pain.setType(jsonType);

        return pain;
    }
}
