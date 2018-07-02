package com.example.anton.ma_ced;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Stool implements JsonSerializer<Stool>, JsonDeserializer<Stool> {
    private int score;
    private String date;
    // TODO private DATENTYP picture
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {

        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public JsonElement serialize(Stool src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("score", getScore());
        jsonObject.addProperty("date", getDate());
        jsonObject.addProperty("time", getTime());

        return jsonObject;
    }

    @Override
    public Stool deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final int jsonScore = jsonObject.get("score").getAsInt();
        final String jsonDate = jsonObject.get("date").getAsString();
        final String jsonTime = jsonObject.get("time").getAsString();

        Stool stool = new Stool();
        stool.setDate(jsonDate);
        stool.setTime(jsonTime);
        stool.setScore(jsonScore);

        return stool;
    }
}
