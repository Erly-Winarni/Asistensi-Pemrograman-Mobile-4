package com.example.h071241065_akmal.model;

import java.io.Serializable;

public class Post implements Serializable {
    private int profileImageResId;
    private String username;
    private int postImageResId;
    private String imageUri;
    private String caption;

    // Constructor data dummy bawaan (menggunakan int Resource ID)
    public Post(int profileImageResId, String username, int postImageResId, String caption) {
        this.profileImageResId = profileImageResId;
        this.username = username;
        this.postImageResId = postImageResId;
        this.caption = caption;
        this.imageUri = null; // Kosongkan karena pakai data dummy
    }

    // Constructor 2: Untuk data upload baru (menggunakan String URI)
    public Post(int profileImageResId, String username, String imageUri, String caption) {
        this.profileImageResId = profileImageResId;
        this.username = username;
        this.imageUri = imageUri;
        this.caption = caption;
        this.postImageResId = 0; // Kosongkan karena pakai gambar galeri
    }

    public int getProfileImageResId() { return profileImageResId; }
    public String getUsername() { return username; }
    public int getPostImageResId() { return postImageResId; }
    public String getImageUri() { return imageUri; }
    public String getCaption() { return caption; }
}