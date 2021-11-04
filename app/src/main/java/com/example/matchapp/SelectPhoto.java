package com.example.matchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectPhoto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
    }
    public void next(View view){
        Intent intent=new Intent(SelectPhoto.this,SelectHobbies.class);
        startActivity(intent);
    }
}