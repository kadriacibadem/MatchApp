package com.example.matchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondScreen extends AppCompatActivity {
    Button logInButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        logInButton=(Button) findViewById(R.id.login);
        signUpButton=(Button) findViewById(R.id.signup);
    }
    public void login(View view){
        Intent intent=new Intent(SecondScreen.this,LogInActivity.class);
        startActivity(intent);
    }
    public void signup(View view){
        Intent intent=new Intent(SecondScreen.this,RegisterActivity.class);
        startActivity(intent);

    }

}