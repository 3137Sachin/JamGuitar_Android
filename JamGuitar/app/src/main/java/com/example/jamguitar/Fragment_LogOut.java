package com.example.jamguitar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Fragment_LogOut extends FragmentActivity {

    //Initialize logout button parameter
    Button btnLogOut;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log_Out button method and connect to XML page
        btnLogOut = findViewById(R.id.LogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent InttoMain = new Intent(Fragment_LogOut.this, MainActivity.class);
                startActivity(InttoMain);
            }
        });

    }

}
