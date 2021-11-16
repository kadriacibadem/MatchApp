package com.example.macthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText email,password;
    String eMail,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.EmailText);
        password=findViewById(R.id.passwordtext);

    }
    public void logIn(View view){

        String txtEmail=email.getText().toString();
        String txtPassword=password.getText().toString();
        if(!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtPassword)){
            mAuth.signInWithEmailAndPassword(txtEmail,txtPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(LogInActivity.this,"Log In Succesfully",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(LogInActivity.this,MainMenu.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LogInActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(LogInActivity.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
        }
    }
}