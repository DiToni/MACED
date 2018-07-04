package com.example.anton.ma_ced;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

public class CreateStool extends AppCompatActivity {
    //TimePicker
    private EditText time;
    private SeekBar seekBar;
    java.util.Calendar mcurrentTime = java.util.Calendar.getInstance();
    private int hour = mcurrentTime.get(java.util.Calendar.HOUR_OF_DAY);
    private int minute = mcurrentTime.get(Calendar.MINUTE);
    private int year= Patient.instance().getCurrentCalendar().get(Calendar.YEAR);
    private int month=Patient.instance().getCurrentCalendar().get(Calendar.MONTH);
    private int date= Patient.instance().getCurrentCalendar().get(Calendar.DATE);
    DecimalFormat df= new DecimalFormat("00");
    java.util.Calendar zeit=  java.util.Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stool);

        //TimePicker
        time = (EditText) findViewById(R.id.stooltime);
        time.setText(df.format(hour) + ":" + df.format(minute));
        zeit.set(year, month, date, hour, minute);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateStool.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(df.format(selectedHour) + ":" + df.format(selectedMinute));
                        zeit.set(year, month, date, selectedHour, selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Uhrzeit auswählen");
                mTimePicker.show();

            }
        });
        seekBar = (SeekBar) findViewById(R.id.seekBar);
    }

    public void onClickButtonWeiter(final View openView) {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Patient.class, Patient.instance());

        final Gson gson = gsonBuilder.create();

        final Stool stool = new Stool();
        stool.setScore(seekBar.getProgress());
        stool.setCalendar(zeit);
        //stool.setTime(time.getText().toString());
        //stool.setDate("13.April 2018");

        Patient.instance().addStool(stool);


        final String json = gson.toJson(Patient.instance());
        System.out.println(json);

        FileOutputStream fos = null;

        try {
            fos = openFileOutput("serialize.json", MODE_PRIVATE);
            fos.write(json.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "serialize.txt",
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//todo: delete activity from back stack:
        Intent intent = new Intent(this, StoolList.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);//FLAG_ACTIVITY_NEW_TASK: if used to start the root activity of a task, it will bring any currently running instance of that task to the foreground, and then clear it to its root state.
        startActivity(intent);
        finish(); // call this to finish the current activity
    }

    public void onClickButtonCamera(final View openView){
        capturePhoto();
    }

    public void onClickButtonQuestionMark(final View openView){
        Intent intent = new Intent(getApplicationContext(), StoolScale.class);
        startActivity(intent);
    }

    /*public void onClickButtonTimePicker (final View openView){
        Intent intent = new Intent(getApplicationContext(), TimePicker.class);
        startActivity(intent);
    }*/

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), StoolList.class);
        startActivity(intent);
    }


    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(intent, 1);
        }
    }



    /*public String getTime(){
        int hour = tp.getHour();
        int min = tp.getMinute();
        return hour + ":" + min;
    }*/







}
