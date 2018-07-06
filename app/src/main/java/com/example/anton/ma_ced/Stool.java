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

public class Stool implements JsonSerializer<Stool>, JsonDeserializer<Stool> {
    private int score;
    private Calendar calendar;//todo serialize and so on
    // TODO private DATENTYP picture

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public JsonElement serialize(Stool src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("score", getScore());

        final JsonElement calendar = context.serialize(getCalendar());
        jsonObject.add("calendar", calendar);

        return jsonObject;
    }

    @Override
    public Stool deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final int jsonScore = jsonObject.get("score").getAsInt();
        calendar  = context.deserialize(jsonObject.get("calendar"), Calendar.class);

        Stool stool = new Stool();
        stool.setScore(jsonScore);
        stool.setCalendar(calendar);

        return stool;
    }
}
