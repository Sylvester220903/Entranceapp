package com.example.entranceexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText password, fullName, email, pno;
    Button register;
    TextView login;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(getApplicationContext());

        fullName = findViewById(R.id.regFullName);
        password = findViewById(R.id.regPassword);
        email = findViewById(R.id.regEmail);
        register = findViewById(R.id.regRegister);
        pno = findViewById(R.id.regPhoneNumber);
        login = findViewById(R.id.regLogin);
        progressBar = findViewById(R.id.regProgressBar);
        fAuth=FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                final String fn = fullName.getText().toString().trim();
                final String e = email.getText().toString().trim();
                final String p = password.getText().toString().trim();
                final String pn = pno.getText().toString().trim();

                if (TextUtils.isEmpty(e)) {
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(p)) {
                    password.setError("Password is required");
                    return;
                }

                if (TextUtils.isEmpty(fn)) {
                    fullName.setError("Full Name is required");
                    return;
                }

                if (TextUtils.isEmpty(pn)) {
                    pno.setError("Phone Number is required");
                    return;
                }

                // firebase reg
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.VISIBLE);

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference docref = fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname", fn);
                            user.put("email",e);
                            user.put("phone",pn);
                            docref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Uid", "successful");
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

    }
}
