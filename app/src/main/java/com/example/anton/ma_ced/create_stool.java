package com.example.anton.ma_ced;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class create_stool extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stool);
    }

    public void onClickButtonWeiter(final View openView) {
        Intent intent = new Intent(getApplicationContext(), activity_stool_list.class);
        startActivity(intent);
    }

    public void onClickButtonCamera(final View openView){
        capturePhoto();
    }

    public void onClickButtonQuestionMark(final View openView){
        Intent intent = new Intent(getApplicationContext(), stool_scale.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), activity_stool_list.class);
        startActivity(intent);
    }


    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(intent, 1);
        }
    }

    public void textFeldLesen(){
        EditText et = (EditText) findViewById(R.id.editText);
        String s = et.getText().toString();
        Log.i("String", s);
    }



}
