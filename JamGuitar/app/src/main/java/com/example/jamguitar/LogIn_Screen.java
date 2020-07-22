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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn_Screen extends AppCompatActivity {

    //Initilize buttons parameters
    EditText emailID,password;
    Button BtnSignIn;
    TextView TvSignUp, Btnforgot;
    FirebaseAuth mFireBaseAuth;

    private FirebaseAuth.AuthStateListener mauthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in__screen);

        //Connect the buttons to the XML page
        mFireBaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.Email2);
        password = findViewById(R.id.Password2);
        TvSignUp = findViewById(R.id.signup);
        BtnSignIn = findViewById(R.id.login);
        Btnforgot = findViewById(R.id.forgot);

        //Check the user if he/she is stored in the firebase database
        mauthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(LogIn_Screen.this, "You are Logged in!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogIn_Screen.this, Registered_User_Screen.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LogIn_Screen.this, "Please Log in!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        //Sign_In button method
        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //User inputs email and password and is checked for incorrect attempts
                String email = emailID.getText().toString();
                String  pwd = password.getText().toString();

                if(email.isEmpty()){
                    emailID.setError("Please enter your email ID");
                    emailID.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LogIn_Screen.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())) {
                    mFireBaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LogIn_Screen.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LogIn_Screen.this, "LogIn Error, Please Try Again!", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent IntToHome = new Intent(LogIn_Screen.this, Registered_User_Screen.class);
                                startActivity(IntToHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LogIn_Screen.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }

            }

        });

                //Sign_Up button method
                 TvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intsignup = new Intent(LogIn_Screen.this, MainActivity.class);
                startActivity(Intsignup);
            }
        });

        //Forgot Password Button
        Btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(LogIn_Screen.this, Forgot_Password.class);
                startActivity(forgot);
            }
        });

    }
                //On_Start method
                 @Override
                protected void onStart() {
                    super.onStart();
                    mFireBaseAuth.addAuthStateListener(mauthStateListener);


                 }
    }

