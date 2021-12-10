package com.example.macthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class Match extends AppCompatActivity {
    ImageView imageView;
    TextView name,age,hobbies,mail;
    EditText nameEdit,ageEdit,emailEdit,hobbiesEdit;
    Button send,restart;
    Random random=new Random();
    ArrayList<String> array=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        imageView=findViewById(R.id.imageView4);
        name=findViewById(R.id.nameText);
        age=findViewById(R.id.ageText);
        mail=findViewById(R.id.mailText);
        hobbies=findViewById(R.id.hobbies);
        nameEdit=findViewById(R.id.nameEdit);
        ageEdit=findViewById(R.id.ageEdit);
        emailEdit=findViewById(R.id.emailEdit);
        hobbiesEdit=findViewById(R.id.hobbiesEdit);
        send=findViewById(R.id.send);
        restart=findViewById(R.id.restart);


        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

    

        array.add("0CqJFgLb9zOlXCSHekLTTj203wD3");
        array.add("0XmL1euEv0N4CiPVsC7LeVz4OEC2");
        array.add("3LWh3xMNHXbIrYLjWnlqSZQrRH73");
        array.add("4K8GHCNJbmQDnY4x6RnHRTAFcUT2");
        array.add("4zbe9tAcmQboBO23NXRCnCQjlcX2");
        array.add("67yngCU5NjfngETRYfQ6vK7Kefr1");
        array.add("7qKF3qijQpSiyVsdJqKKfZ8i1j52");
        array.add("8oUauvi3oDZFYmZBmb2oh9awaly2");
        array.add("APTQPlmAuRN9xQ9Pwy8IR8AiHct2");
        array.add("GJbY9hJpPSgvuZNqe8599U9YAmy2");

        String randomUserId=array.get(random.nextInt(array.size()));


        DocumentReference reference;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        reference=firestore.collection("Users").document(randomUserId);

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
    public void againMatch(View view){
        Intent intent=new Intent(Match.this,Match.class);
        startActivity(intent);
    }
}
