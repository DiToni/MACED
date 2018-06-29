package com.example.anton.ma_ced;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

public class CreateStool extends AppCompatActivity {
    //TimePicker
    private EditText time;
    java.util.Calendar mcurrentTime = java.util.Calendar.getInstance();
    private int hour = mcurrentTime.get(java.util.Calendar.HOUR_OF_DAY);
    private int minute = mcurrentTime.get(Calendar.MINUTE);
    DecimalFormat df= new DecimalFormat("00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stool);

        //TimePicker
        time = (EditText) findViewById(R.id.stooltime);
        time.setText(df.format(hour) + ":" + df.format(minute));
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateStool.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(df.format(selectedHour) + ":" + df.format(selectedMinute));
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Uhrzeit auswählen");
                mTimePicker.show();

            }
        });
    }

    public void onClickButtonWeiter(final View openView) {
        Intent intent = new Intent(getApplicationContext(), StoolList.class);
        startActivity(intent);
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
