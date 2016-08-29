package com.example.tangcan0823.mintia_omiyagego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AllControl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_control);
        int i=1;
        if(i==1){
            Intent intent = new Intent(getApplicationContext(),VideoControl.class);//open the page
            startActivity(intent);
            i++;
        }else{
            Intent intent = new Intent(getApplicationContext(),MainControl.class);//open the page
            startActivity(intent);

        }

    }
}
