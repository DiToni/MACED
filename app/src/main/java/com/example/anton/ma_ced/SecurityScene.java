package com.example.anton.ma_ced;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

public class SecurityScene extends AppCompatActivity {

    private Boolean switchState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_scene);

        final Switch switch2 = (Switch) findViewById(R.id.switch2);
        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch2.isChecked()){
                   switchState = false;

                }else{
                    switchState = true;
                }
            }
        });

    }

    public boolean returnSwitch() {
        return switchState;
    }


}
