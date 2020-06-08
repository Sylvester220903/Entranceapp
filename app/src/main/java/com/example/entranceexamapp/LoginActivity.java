package com.example.entranceexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText password, email;
    Button login;
    TextView reg;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    private final String CHANNEL_ID ="personal_notification";
    public String ab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);
        progressBar = findViewById(R.id.progressBar);


        fAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e = email.getText().toString().trim();
                String p = password.getText().toString().trim();

                if (TextUtils.isEmpty(e)) {
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(p)) {
                    password.setError("Password is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //auth the user

                fAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), QuestionsActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    public void notify1(String ab){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name="Personal notification";
            String description="Include all";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationchannel=new NotificationChannel(CHANNEL_ID,name,importance);
            notificationchannel.setDescription(description);
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationchannel);

        }

//        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this,CHANNEL_ID);
//        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background);
//        notificationBuilder.setContentTitle("pickmymovie");
//        //Toast.makeText(MainActivity.this,ab, Toast.LENGTH_SHORT).show();
//        notificationBuilder.setContentText(ab);
//        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(ab));
//        notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        NotificationManagerCompat notificationmanagercompat= NotificationManagerCompat.from(this);
//        notificationmanagercompat.notify(000,notificationBuilder.build());
    }
}
