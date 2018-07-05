package com.example.anton.ma_ced;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class EventSerialiser implements JsonSerializer<Event>, JsonDeserializer<Event> {

    @Override
    public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final int color = jsonObject.get("color").getAsInt();
        final long timeInMillis = jsonObject.get("timeInMillis").getAsLong();
        System.out.println(jsonObject.get("timeInMillis").getAsString());
        //final Object data = jsonObject.get("data").getAsString(); //data won't be serialized

        return new Event(color, timeInMillis);
    }

    @Override
    public JsonElement serialize(Event src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("color", src.getColor());
        jsonObject.addProperty("timeInMillis", src.getTimeInMillis());
        //jsonObject.addProperty("data", src.getData().toString()); //data won't be serialized

        return jsonObject;
    }
}
