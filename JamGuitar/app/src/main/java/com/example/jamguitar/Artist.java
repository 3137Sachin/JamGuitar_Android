package com.example.jamguitar;

public class Artist {

    //A main class to initialize the database components in this class
    String artistID;
    String artistName;
    String artistGenre;

    public Artist(){

    }

    public Artist(String artistID, String artistName, String artistGenre) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public String getArtistID(){
        return artistID;
    }

    public String getArtistName(){
        return artistName;
    }

    public String getArtistGenre(){
        return artistGenre;
    }

}
