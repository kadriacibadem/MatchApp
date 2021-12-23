package com.example.macthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainMenu extends AppCompatActivity {
    ImageView imageView;
    TextView name,age,hobbies,mail;
    EditText nameEdit,ageEdit,emailEdit,hobbiesEdit;
    Button button1,button2;
    ArrayList<String> userIdArray=new ArrayList<String>();
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        imageView=findViewById(R.id.imageView4);
        name=findViewById(R.id.nameText);
        age=findViewById(R.id.ageText);
        mail=findViewById(R.id.mailText);
        hobbies=findViewById(R.id.hobbies);
        nameEdit=findViewById(R.id.nameEdit);
        ageEdit=findViewById(R.id.ageEdit);
        emailEdit=findViewById(R.id.emailEdit);
        hobbiesEdit=findViewById(R.id.hobbiesEdit);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);


        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String currentid=user.getUid();
        userIdArray.add(currentid);
        for (String i:userIdArray){
            System.out.println(i);
        }
        DocumentReference reference;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();

        reference=firestore.collection("Users").document(currentid);

        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    DocumentSnapshot document= task.getResult();
                    String nameResult=task.getResult().getString("name");
                    String ageResult=task.getResult().getString("age");
                    String url=task.getResult().getString("profile_photo");
                    String eMail=task.getResult().getString("email");

                    List<String> hobbiesResult=(List<String>) document.get("Hobbies");

                    Map<String, Object> map = document.getData();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        if (entry.getKey().equals("Hobbies")) {
                          Log.d("TAG", entry.getValue().toString());
                          hobbies.setText(entry.getValue().toString());

                        }
                    }
                    Picasso.get().load(url).into(imageView);
                    name.setText(nameResult);
                    mail.setText(eMail);
                    age.setText(ageResult);
                }
            }
        });
    }
  /* public String getRandomUserId() {
        Random random=new Random();
        int index = random.nextInt(userIdArray.size()+1);
        String randomUserId=userIdArray.get(index);
        return randomUserId;


    }*/
    public void match(View view){
        Intent intent=new Intent(MainMenu.this,MatchPreparing.class);
        startActivity(intent);

    }

    public void messages(View view){
        Intent intent=new Intent(MainMenu.this,Messages.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.bottom_nav_ic_signOut){

            firebaseAuth.signOut();
            Intent intent=new Intent(MainMenu.this,LogInActivity.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.bottom_nav_ic_macth){

            Intent intent=new Intent(MainMenu.this,Match.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}