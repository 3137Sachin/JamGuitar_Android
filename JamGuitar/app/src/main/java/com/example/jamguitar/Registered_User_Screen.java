package com.example.jamguitar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;


//This is a class for the registered users only
public class Registered_User_Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        //Initializing parameters for Logout Button
        private FirebaseAuth.AuthStateListener mAuthStateListener;

        //Initializing Navigation Drawer variables
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;

    //Music Buttons Images, Download URls, individual Media Players
    ImageButton playsong1, playsong2, playsong3, playsong4, playsong5;
    Uri myUri1 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FC%20Major%20Pop%20Rock%20Style%20Backing%20Track.mp3?alt=media&token=1657e708-2645-4701-9b59-823fe7706721");
    Uri myUri2 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FJack%20Thammarat%20-%20Boss%20Thailand%20BT%20144BPM.mp3?alt=media&token=244ec083-90f1-4607-ba99-d8acafe7e91e");
    Uri myUri3 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FSample%20%231.mp3?alt=media&token=0c7af161-973e-4f8b-9e2d-3e8789266466");
    Uri myUri4 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FJack%20Thammarat%20-%20Dorian%20Change%20Backing%20Track.mp3?alt=media&token=4e5cc918-99fe-4693-99e0-c9412455e26c");
    Uri myUri5 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/jam-guitar-1e65b.appspot.com/o/Songs%2FJack%20Thammarat%20-%20B%20Minor%20Jam%20Backing%20Track.mp3?alt=media&token=b9f0cd42-d4eb-4260-98e0-2b8de6dcb916");
    MediaPlayer mdx1, mdx2, mdx3, mdx4, mdx5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered__user__screen);


        //Media Player and Button for Song#1
        mdx1 = MediaPlayer.create(Registered_User_Screen.this, myUri1);
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
        mdx2 = MediaPlayer.create(Registered_User_Screen.this, myUri2);
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
        mdx3 = MediaPlayer.create(Registered_User_Screen.this, myUri3);
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

        //Media Player and Button for Song#4
        mdx4 = MediaPlayer.create(Registered_User_Screen.this, myUri4);
        playsong4 = (ImageButton)findViewById(R.id.playsong4);
        playsong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mdx4.isPlaying()) {
                    mdx4.pause();
                    playsong4.setBackgroundResource(R.drawable.e_minor_play);
                } else {
                    mdx4.start();
                    playsong4.setBackgroundResource(R.drawable.e_minor_pause);
                }
            }
        });

        //Media Player and Button for Song#5
        mdx5 = MediaPlayer.create(Registered_User_Screen.this, myUri5);
        playsong5 = (ImageButton)findViewById(R.id.playsong5);
        playsong5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mdx5.isPlaying()) {
                    mdx5.pause();
                    playsong5.setBackgroundResource(R.drawable.b_minor_play);
                } else {
                    mdx5.start();
                    playsong5.setBackgroundResource(R.drawable.b_minor_pause);
                }
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Connect drawer and navigation view to XML page
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //When Items are selected
        navigationView.setNavigationItemSelectedListener(this);

        //Method for action bar toggled (Opened or Closed)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Home()).commit();
            navigationView.setCheckedItem(R.id.home_frag);
        }

    }
    //When the empty screen is clicked the drawer closes
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
    //Method when the items in the navigation pane is selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Home()).commit();
                break;
            case R.id.Videos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Videos()).commit();
                break;
            case R.id.Guitar_Scales:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Guitar_Scales()).commit();
                break;
            case R.id.Tips:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Tips()).commit();
                break;
            case R.id.About:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_About()).commit();
                break;
            case R.id.Log_Out:
                FirebaseAuth.getInstance().signOut();
                Intent t = new Intent(Registered_User_Screen.this, MainActivity.class);
                startActivity(t);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Link to open videos in video tab
    public void openonelink(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=13SVQNhzYxg"));
        startActivity(browserIntent);
    }

    //Link to open videos in video tab
    public void opentwolink(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=7BrjpymzpLU"));
        startActivity(browserIntent);
    }


    }





