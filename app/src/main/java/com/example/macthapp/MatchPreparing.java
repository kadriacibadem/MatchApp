package com.example.macthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MatchPreparing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_preparing);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(MatchPreparing.this,Match.class);
                startActivity(i);
                finish();
            }
        },4500);
    }
}