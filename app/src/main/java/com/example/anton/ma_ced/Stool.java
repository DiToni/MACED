package com.example.anton.ma_ced;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Stool implements JsonSerializer<Stool> {
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
}
