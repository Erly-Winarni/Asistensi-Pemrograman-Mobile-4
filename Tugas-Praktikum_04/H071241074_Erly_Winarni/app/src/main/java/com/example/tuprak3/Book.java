package com.example.tuprak3;

import android.net.Uri;

public class Book {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String author;
    private int year;
    private String blurb;
    private String genre;
    private float rating;
    private String review;
    private boolean liked;
    private int coverResId;
    private Uri coverUri;

    public Book(String title, String author, int year, String blurb,
                String genre, float rating, String review, int coverResId) {
        this.id         = idCounter++;
        this.title      = title;
        this.author     = author;
        this.year       = year;
        this.blurb      = blurb;
        this.genre      = genre;
        this.rating     = rating;
        this.review     = review;
        this.coverResId = coverResId;
        this.liked      = false;
    }

    public Book(String title, String author, int year, String blurb,
                String genre, float rating, String review, Uri coverUri) {
        this.id       = idCounter++;
        this.title    = title;
        this.author   = author;
        this.year     = year;
        this.blurb    = blurb;
        this.genre    = genre;
        this.rating   = rating;
        this.review   = review;
        this.coverUri = coverUri;
        this.liked    = false;
    }

    public int     getId()         { return id; }
    public String  getTitle()      { return title; }
    public String  getAuthor()     { return author; }
    public int     getYear()       { return year; }
    public String  getBlurb()      { return blurb; }
    public String  getGenre()      { return genre; }
    public float   getRating()     { return rating; }
    public String  getReview()     { return review; }
    public boolean isLiked()       { return liked; }
    public int     getCoverResId() { return coverResId; }
    public Uri     getCoverUri()   { return coverUri; }
    public void setLiked(boolean liked) { this.liked = liked; }
}