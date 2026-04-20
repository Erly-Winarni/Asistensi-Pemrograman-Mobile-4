package com.example.tuprak1;

public class FeedItem {
    private String username;
    private String location;
    private String caption;
    private String time;
    private int likes;
    private int avatarRes;
    private int imageRes;
    private String fullName;
    private String bio;
    private String pronouns;
    private int followers;
    private int following;
    private int postCount;
    private int[] postImages;
    private HighlightItem[] highlights;

    public FeedItem(String username, String location, String caption, String time, int likes,
                    int avatarRes, int imageRes, String fullName, String bio, String pronouns,
                    int followers, int following, int postCount, int[] postImages,
                    HighlightItem[] highlights) {
        this.username = username;
        this.location = location;
        this.caption = caption;
        this.time = time;
        this.likes = likes;
        this.avatarRes = avatarRes;
        this.imageRes = imageRes;
        this.fullName = fullName;
        this.bio = bio;
        this.pronouns = pronouns;
        this.followers = followers;
        this.following = following;
        this.postCount = postCount;
        this.postImages = postImages;
        this.highlights = highlights;
    }

    public String getUsername() { return username; }
    public String getLocation() { return location; }
    public String getCaption() { return caption; }
    public String getTime() { return time; }
    public int getLikes() { return likes; }
    public int getAvatarRes() { return avatarRes; }
    public int getImageRes() { return imageRes; }
    public String getFullName() { return fullName; }
    public String getBio() { return bio; }
    public String getPronouns() { return pronouns; }
    public int getFollowers() { return followers; }
    public int getFollowing() { return following; }
    public int getPostCount() { return postCount; }
    public int[] getPostImages() { return postImages; }
    public HighlightItem[] getHighlights() { return highlights; }
}
