package com.example.h071241065_akmal.model;

public class Highlight {
    private int coverImageResId;
    private String title;

    public Highlight(int coverImageResId, String title) {
        this.coverImageResId = coverImageResId;
        this.title = title;
    }

    public int getCoverImageResId() { return coverImageResId; }
    public String getTitle() { return title; }
}