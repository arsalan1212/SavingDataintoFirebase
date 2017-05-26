package com.example.arsalankhan.savingdataintofirebase;

/**
 * Created by Arsalan khan on 5/26/2017.
 */

public class Artist {
    String id;
    String name;
    String artist;


    public Artist(String id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }


    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }



    public Artist(){

    }

}
