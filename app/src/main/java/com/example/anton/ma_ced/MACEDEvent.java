package com.example.anton.ma_ced;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MACEDEvent extends Event implements JsonSerializer<MACEDEvent>, JsonDeserializer<MACEDEvent> {

    public MACEDEvent(int color, long timeInMillis) {
        super(color, timeInMillis);
    }

    public MACEDEvent(int color, long timeInMillis, Object data) {
        super(color, timeInMillis, data);
    }

    @Override
    public MACEDEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(MACEDEvent src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
