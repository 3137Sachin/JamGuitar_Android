package com.example.jamguitar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GuestUser_Screen extends AppCompatActivity {

    //Initializing parameters for Logout
    Button btnLogOut;

    //Initializing spinner and edit text names and add songs Button
    Button buttonAdd;
    EditText editTextName;
    Spinner spinnerGenres;

    //Database reference object initialization
    DatabaseReference databaseArtist;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //Music Buttons Images, Download URls, individual Media Players
    ImageButton playsong1, playsong2, playsong3;
    Uri myUri1 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FC%20Major%20Pop%20Rock%20Style%20Backing%20Track.mp3?alt=media&token=1657e708-2645-4701-9b59-823fe7706721");
    Uri myUri2 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FJack%20Thammarat%20-%20Boss%20Thailand%20BT%20144BPM.mp3?alt=media&token=244ec083-90f1-4607-ba99-d8acafe7e91e");
    Uri myUri3 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FSample%20%231.mp3?alt=media&token=0c7af161-973e-4f8b-9e2d-3e8789266466");
    MediaPlayer mdx1, mdx2, mdx3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set content view when changing the activities
        setContentView(R.layout.activity_guest_user__screen);

        //Link buttons to XML page
        buttonAdd = findViewById(R.id.buttonAddArtist);
        editTextName = findViewById(R.id.editTextName);
        spinnerGenres = findViewById(R.id.spinnerGenres);

        //Set on click listener to add songs button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addArtist();
            }
        });

        //Initialize database to firebase
        databaseArtist = FirebaseDatabase.getInstance().getReference("artist");

        //Media Player and Button for Song#1
        mdx1 = MediaPlayer.create(GuestUser_Screen.this, myUri1);
        playsong1 = (ImageButton)findViewById(R.id.playsong1);
        playsong1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mdx1.isPlaying()) {
                        mdx1.pause();
                        playsong1.setBackgroundResource(R.drawable.c_major_play);
                    } else {
                        mdx1.start();
                        playsong1.setBackgroundResource(R.drawable.c_major_pause);
                    }
                }
            });

        //Media Player and Button for Song#2
        mdx2 = MediaPlayer.create(GuestUser_Screen.this, myUri2);
        playsong2 = (ImageButton)findViewById(R.id.playsong2);
        playsong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mdx2.isPlaying()) {
                    mdx2.pause();
                    playsong2.setBackgroundResource(R.drawable.d_minor_play);
                } else {
                    mdx2.start();
                    playsong2.setBackgroundResource(R.drawable.d_minor_pause);
                }
            }
        });

        //Media Player and Button for Song#3
        mdx3 = MediaPlayer.create(GuestUser_Screen.this, myUri3);
        playsong3 = (ImageButton)findViewById(R.id.playsong3);
        playsong3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mdx3.isPlaying()) {
                    mdx3.pause();
                    playsong3.setBackgroundResource(R.drawable.e_minor2_play);
                } else {
                    mdx3.start();
                    playsong3.setBackgroundResource(R.drawable.e_minor2_pause);
                }
            }
        });

        //Log_Out button method and connect to XML page
        btnLogOut = findViewById(R.id.LogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent InttoMain = new Intent(GuestUser_Screen.this, MainActivity.class);
                startActivity(InttoMain);
            }
        });


    }

    //method to add Artists with with unique id, name and genre to the firebase database
    private void addArtist(){
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();

        //if-else method to upload name in the database. FYI: Every time new ID will be generated
        if(!TextUtils.isEmpty(name)){
            String id = databaseArtist.push().getKey();
            Artist artist = new Artist(id, name, genre);
            databaseArtist.child(id).setValue(artist);
            Toast.makeText(this, "Added to Database Successfully!!", Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(this, "You should enter a Artist name", Toast.LENGTH_SHORT).show();

        }
    }



}