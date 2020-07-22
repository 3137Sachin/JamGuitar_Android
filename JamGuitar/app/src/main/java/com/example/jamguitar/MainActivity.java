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
import com.google.firebase.auth.FirebaseAuthException;

        //This is the main class that is the login and creating new user account
public class MainActivity extends AppCompatActivity {

    //Initializing parameters for login/logout/guest user/sign up buttons
    EditText emailID,password;
    Button BtnSignUp;
    TextView TvSignIn;
    FirebaseAuth mFireBaseAuth;
    Button Guest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect the buttons with the XML page
        mFireBaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.Email1);
        password = findViewById(R.id.Password1);
        TvSignIn = findViewById(R.id.login);
        BtnSignUp = findViewById(R.id.signup);
        Guest = findViewById(R.id.guest1);

        //Sign-Up button function when clicked
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Email and Password of the user
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
                    Toast.makeText(MainActivity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFireBaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please Try Again!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(MainActivity.this, Registered_User_Screen.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }

            }
        });



        //Sign_In button method
        TvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LogIn_Screen.class);
                startActivity(i);
            }
        });

        //Guest Login button method
        Guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GuestUser_Screen.class);
                startActivity(i);
            }
        });

        }

    }



