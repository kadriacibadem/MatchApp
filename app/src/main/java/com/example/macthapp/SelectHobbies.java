package com.example.macthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectHobbies extends AppCompatActivity {
    DocumentReference mRef;
    FirebaseFirestore mFireStore;
    FirebaseUser mUser;
    User user;
    HashMap<String,Object> mData;
    ArrayList<String> hobbies;
    FirebaseAuth mAuth;
    Button button;
    CheckBox checkBox;
    CheckBox checkBox1;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    CheckBox checkBox9;
    CheckBox checkBox10;
    CheckBox checkBox11;
    CheckBox checkBox12;
    CheckBox checkBox13;
    CheckBox checkBox14;
    CheckBox checkBox15;
    CheckBox checkBox16;
    CheckBox checkBox17;
    CheckBox checkBox18;
    CheckBox checkBox19;
    CheckBox checkBox20;
    CheckBox checkBox21;
    CheckBox checkBox22;
    CheckBox checkBox23;
    CheckBox checkBox24;
    CheckBox checkBox25;
    CheckBox checkBox26;
    CheckBox checkBox27;
    CheckBox checkBox28;
    CheckBox checkBox29;
    CheckBox checkBox30;
    CheckBox checkBox31;
    CheckBox checkBox32;
    CheckBox checkBox33;
    Button registerButon;

    String c="Football";
    String c1="Basketball";
    String c3="Volleyball";
    String c4="Tennis";
    String c5="Cinema";
    String c6="Love Movie";
    String c7="Horror Movie";
    String c8="Comedy Movie";
    String c9="Books";
    String c10="Novel";
    String c11="History";
    String c12="Poem";
    String c13="Swim";
    String c14="Bike";
    String c15="Music";
    String c16="Metal Music";
    String c17="Rap";
    String c18="Classical Music";
    String c19="Instruments";
    String c20="Series";
    String c21="Cook";
    String c22="Food";
    String c23="Dance";
    String c24="Puzzle";
    String c25="Bicycle";
    String c26="Car";
    String c27="Game";
    String c28="Software";
    String c29="Chess";
    String c30="Sing";
    String c31="Photography";
    String c32="Picnic";
    String c33="Traveling";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser= FirebaseAuth.getInstance().getCurrentUser();
        mFireStore=FirebaseFirestore.getInstance();
        mRef=mFireStore.collection("Users").document(mUser.getUid());

        hobbies = new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        registerButon=findViewById(R.id.registerButon);
        setContentView(R.layout.activity_select_hobbies);
        checkBox=findViewById(R.id.checkBox);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);
        checkBox7=findViewById(R.id.checkBox7);
        checkBox8=findViewById(R.id.checkBox8);
        checkBox9=findViewById(R.id.checkBox9);
        checkBox10=findViewById(R.id.checkBox10);
        checkBox11=findViewById(R.id.checkBox11);
        checkBox12=findViewById(R.id.checkBox12);
        checkBox13=findViewById(R.id.checkBox13);
        checkBox14=findViewById(R.id.checkBox14);
        checkBox15=findViewById(R.id.checkBox15);
        checkBox16=findViewById(R.id.checkBox16);
        checkBox17=findViewById(R.id.checkBox17);
        checkBox18=findViewById(R.id.checkBox18);
        checkBox19=findViewById(R.id.checkBox19);
        checkBox20=findViewById(R.id.checkBox20);
        checkBox21=findViewById(R.id.checkBox21);
        checkBox22=findViewById(R.id.checkBox22);
        checkBox23=findViewById(R.id.checkBox23);
        checkBox24=findViewById(R.id.checkBox24);
        checkBox25=findViewById(R.id.checkBox25);
        checkBox26=findViewById(R.id.checkBox26);
        checkBox27=findViewById(R.id.checkBox27);
        checkBox28=findViewById(R.id.checkBox28);
        checkBox29=findViewById(R.id.checkBox29);
        checkBox30=findViewById(R.id.checkBox30);
        checkBox31=findViewById(R.id.checkBox31);
        checkBox32=findViewById(R.id.checkBox32);
        checkBox33=findViewById(R.id.checkBox33);

    }
    public void isSelected(){

        if(checkBox.isChecked()){
            hobbies.add(c);
        }else{
            hobbies.remove(c);
        }
        if(checkBox1.isChecked()){
            hobbies.add(c1);
        }else{
            hobbies.remove(c1);
        }
        if(checkBox3.isChecked()){
            hobbies.add(c3);
        }else{
            hobbies.remove(c3);
        }
        if(checkBox4.isChecked()){
            hobbies.add(c4);
        }else{
            hobbies.remove(c4);
        }
        if(checkBox5.isChecked()){
            hobbies.add(c5);
        }else{
            hobbies.remove(c5);
        }
        if(checkBox6.isChecked()){
            hobbies.add(c6);
        }else{
            hobbies.remove(c6);
        }
        if(checkBox7.isChecked()){
            hobbies.add(c7);
        }else{
            hobbies.remove(c7);
        }
        if(checkBox8.isChecked()){
            hobbies.add(c8);
        }else{
            hobbies.remove(c8);
        }
        if(checkBox9.isChecked()){
            hobbies.add(c9);
        }else{
            hobbies.remove(c9);
        }
        if(checkBox10.isChecked()){
            hobbies.add(c10);
        }else{
            hobbies.remove(c10);
        }
        if(checkBox11.isChecked()){
            hobbies.add(c11);
        }else{
            hobbies.remove(c11);
        }
        if(checkBox12.isChecked()){
            hobbies.add(c12);
        }else{
            hobbies.remove(c12);
        }
        if(checkBox13.isChecked()){
            hobbies.add(c13);
        }else{
            hobbies.remove(c13);
        }
        if(checkBox14.isChecked()){
            hobbies.add(c14);
        }else{
            hobbies.remove(c14);
        }
        if(checkBox15.isChecked()){
            hobbies.add(c15);
        }else{
            hobbies.remove(c15);
        }
        if(checkBox16.isChecked()){
            hobbies.add(c16);
        }else{
            hobbies.remove(c16);
        }
        if(checkBox17.isChecked()){
            hobbies.add(c17);
        }else{
            hobbies.remove(c17);
        }
        if(checkBox18.isChecked()){
            hobbies.add(c18);
        }else{
            hobbies.remove(c18);
        }
        if(checkBox19.isChecked()){
            hobbies.add(c19);
        }else{
            hobbies.remove(c19);
        }
        if(checkBox20.isChecked()){
            hobbies.add(c20);
        }else{
            hobbies.remove(c20);
        }
        if(checkBox21.isChecked()){
            hobbies.add(c21);
        }else{
            hobbies.remove(c21);
        }
        if(checkBox22.isChecked()){
            hobbies.add(c22);
        }else{
            hobbies.remove(c22);
        }
        if(checkBox23.isChecked()){
            hobbies.add(c23);
        }else{
            hobbies.remove(c23);
        }
        if(checkBox24.isChecked()){
            hobbies.add(c24);
        }else{
            hobbies.remove(c24);
        }
        if(checkBox25.isChecked()){
            hobbies.add(c25);
        }else{
            hobbies.remove(c25);
        }
        if(checkBox26.isChecked()){
            hobbies.add(c26);
        }else{
            hobbies.remove(c26);
        }
        if(checkBox27.isChecked()){
            hobbies.add(c27);
        }else{
            hobbies.remove(c27);
        }
        if(checkBox28.isChecked()){
            hobbies.add(c28);
        }else{
            hobbies.remove(c28);
        }
        if(checkBox29.isChecked()){
            hobbies.add(c29);
        }else{
            hobbies.remove(c29);
        }
        if(checkBox30.isChecked()){
            hobbies.add(c30);
        }else{
            hobbies.remove(c30);
        }
        if(checkBox31.isChecked()){
            hobbies.add(c31);
        }else{
            hobbies.remove(c31);
        }
        if(checkBox32.isChecked()){
            hobbies.add(c32);
        }else{
            hobbies.remove(c32);
        }
        if(checkBox33.isChecked()){
            hobbies.add(c33);
        }else{
            hobbies.remove(c33);
        }
    }

    public void register(View view){
       isSelected();


           mData=new HashMap<>();
           mData.put("Hobbies",hobbies);
           mFireStore.collection("Users").document(mUser.getUid()).update(mData).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful()){
                       finish();
                       startActivity(new Intent(SelectHobbies.this,PreparingScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                   }
               }
           });
       }
    }
