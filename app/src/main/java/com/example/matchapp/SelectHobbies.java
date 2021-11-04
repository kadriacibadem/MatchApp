package com.example.matchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectHobbies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hobbies);
    }
    public void register(View view){
        Intent intent=new Intent(SelectHobbies.this,PreparingScreen.class);
        startActivity(intent);
    }
}