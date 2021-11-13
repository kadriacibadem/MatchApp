package com.example.matchapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Uri imageUri;
    private static final int PICK_IMAGE=1;
    UploadTask uploadTask;
    FirebaseStorage firebaseStorage;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    StorageReference storageReference;
    DatabaseReference databaseReference;
    DocumentReference documentReference;
    TextView textView;
    ImageView profilePhoto;
    EditText emailText, nameText, passwordText, ageText;
    Button nextButton;
    String userId;
    AllUserMember member;
    String currentUserId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        member=new AllUserMember();
        textView = findViewById(R.id.textView3);
        profilePhoto = findViewById(R.id.imageView4);
        emailText = findViewById(R.id.emailText);
        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
        ageText = findViewById(R.id.ageText);
        nextButton = findViewById(R.id.nextButton);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        currentUserId=user.getUid();

        documentReference=db.collection("user").document(currentUserId);
        storageReference = FirebaseStorage.getInstance().getReference("Profile images");
        databaseReference=database.getReference("All Users");

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode == PICK_IMAGE || resultCode == RESULT_OK || data != null || data.getData() != null){
                imageUri=data.getData();
                Picasso.get().load(imageUri).into(profilePhoto);
            }

        }catch (Exception e){
            Toast.makeText(this,"Error"+e,Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadData(){
        String email = emailText.getText().toString();
        String password=passwordText.getText().toString();
        String name=nameText.getText().toString();
        String age=ageText.getText().toString();
        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(name) || !TextUtils.isEmpty(age) || !TextUtils.isEmpty(password) || imageUri != null){
           final StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+ getFileExt(imageUri));
            uploadTask=reference.putFile(imageUri);
            Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw  task.getException();

                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri dowloadUri=task.getResult();
                        Map<String,String> profile=new HashMap<>();
                        profile.put("email",email);
                        profile.put("name",name);
                        profile.put("age",age);
                        profile.put("url",dowloadUri.toString());
                        member.setEmaiL(email);
                        member.setName(name);
                        member.setAge(age);
                        member.setUid(currentUserId);
                        member.setUrl(dowloadUri.toString());
                        databaseReference.child(currentUserId).setValue(member);

                        documentReference.set(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(RegisterActivity.this,"Now Select Hobbies",Toast.LENGTH_SHORT).show();
                                Intent intent2=new Intent(RegisterActivity.this,SelectHobbies.class);
                                startActivity(intent2);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this,"failed",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }else{
            Toast.makeText(this,"All fields required",Toast.LENGTH_SHORT).show();
        }
    }
}