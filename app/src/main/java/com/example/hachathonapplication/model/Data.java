package com.example.hachathonapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Data {

    //@SerializedName("url")
    private  String url;

    //@SerializedName("song")
    private  String song;
    //@SerializedName("artists")
    private  String artists;
    //@SerializedName("cover_image")
    private  String cover_image;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public Data( String song,String url, String artists, String cover_image) {
        this.url = url;
        this.song = song;
        this.artists = artists;
        this.cover_image = cover_image;
    }
}


