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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarDeSerializer implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.GERMAN);

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        String dateAsString = json.getAsString();
        Calendar calendar = Calendar.getInstance();

        try{
            Date date = simpleDateFormat.parse(dateAsString);
            calendar.setTime(date);
        }catch (ParseException pe){
            pe.printStackTrace();
        }

        return calendar;
    }

    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        
        String calendarAsString = simpleDateFormat.format(src.getTime());
        jsonObject.addProperty("calendar", calendarAsString);

        return  jsonObject;
    }
}
