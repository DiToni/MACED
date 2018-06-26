package com.example.anton.ma_ced;

import android.support.v7.app.AppCompatActivity;

public class ProtectionMechanism extends AppCompatActivity {




    public String changeText(String s){
        if(s.equals("Pin")){
            return "Pin";
        }
        return "Passwort";
    }
}
