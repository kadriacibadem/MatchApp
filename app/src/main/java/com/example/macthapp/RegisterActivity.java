package com.example.macthapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private User user;
    private FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    TextView textView;
    ImageView profilePhoto;
    EditText emailText, nameText, passwordText, ageText;
    Button nextButton;
    Uri imageUri;
    ProgressBar pBar;
    private static final int PICK_IMAGE=1;

    StorageReference mStorageRef,newRef,sRef;
    String kayıtYeri,dowloadLink;
    HashMap<String,Object> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView = findViewById(R.id.textView3);
        profilePhoto = findViewById(R.id.imageView4);
        emailText = findViewById(R.id.emailText);
        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
        ageText = findViewById(R.id.ageText);
        nextButton = findViewById(R.id.nextButton);
        mAuth=FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();
        pBar=findViewById(R.id.pbar);
        pBar.setVisibility(View.INVISIBLE);
        mStorageRef= FirebaseStorage.getInstance().getReference();

    }
    public void next(View view){
        nextButton.setEnabled(false);
        pBar.setVisibility(View.VISIBLE);
        String email = emailText.getText().toString();
        String password=passwordText.getText().toString();
        String name=nameText.getText().toString();
        String age=ageText.getText().toString();

        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(name) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(age)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mUser=mAuth.getCurrentUser();
                        if(mUser != null){
                            user=new User(email,name,age,mUser.getUid());
                            mFirestore.collection("Users").document(mUser.getUid()).set(user).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        kayıtYeri="Users/"+user.getEmail()+"/profile.png";
                                        sRef= mStorageRef.child(kayıtYeri);
                                        sRef.putFile(imageUri)
                                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                        newRef=FirebaseStorage.getInstance().getReference(kayıtYeri);
                                                        newRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri uri) {
                                                                dowloadLink=uri.toString();
                                                                mData=new HashMap<>();
                                                                mData.put("profile_photo",dowloadLink);
                                                                mFirestore.collection("Users").document(mUser.getUid()).update(mData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if(task.isSuccessful()){
                                                                            Toast.makeText(RegisterActivity.this,"Select Hobbies",Toast.LENGTH_SHORT).show();
                                                                            finish();
                                                                            startActivity(new Intent(RegisterActivity.this,SelectHobbies.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                    }
                                }
                            });

                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(RegisterActivity.this,"Please Enter All Field",Toast.LENGTH_SHORT).show();
        }
    }
    public void selectImage(View view){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode == PICK_IMAGE || resultCode == RESULT_OK || data != null || data.getData() !=null ){
                imageUri=data.getData();
                Picasso.get().load(imageUri).into(profilePhoto);
            }

        }catch (Exception e){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT);
        }


    }
    private String getFileExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}