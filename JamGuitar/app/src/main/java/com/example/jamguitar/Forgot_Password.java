package com.example.jamguitar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    //Initializing buttons
    EditText Useremail;
    Button sendpassword;
    TextView GoBack;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        //Linking buttons to the XML page
        Useremail = findViewById(R.id.EmailButton);
        sendpassword = findViewById(R.id.sendpassword);
        GoBack = findViewById(R.id.goback);

        //get firebase authorization
        firebaseAuth = FirebaseAuth.getInstance();

        //Send Password Button
        sendpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(Useremail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Forgot_Password.this, "Your Password reset email has been sent!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Forgot_Password.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //Go back to Login Screen Button method
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(Forgot_Password.this, LogIn_Screen.class);
                startActivity(forgot);
            }
        });

    }


}
