package com.example.tuprak1;

import java.util.ArrayList;
import java.util.List;

public class PostManager {

    public static class Post {
        public String imagePath;
        public int    imageRes;
        public String caption;
        public boolean isFile;

        public Post(String imagePath, String caption) {
            this.imagePath = imagePath;
            this.caption   = caption;
            this.isFile    = true;
        }

        public Post(int imageRes, String caption) {
            this.imageRes = imageRes;
            this.caption  = caption;
            this.isFile   = false;
        }
    }

    private static PostManager instance;
    private final List<Post> posts = new ArrayList<>();

    private PostManager() {
        posts.add(new Post(R.drawable.f1, "caption postingan pertama"));
        posts.add(new Post(R.drawable.f2, "caption postingan kedua"));
        posts.add(new Post(R.drawable.f3, "caption postingan ketiga"));
        posts.add(new Post(R.drawable.f4, "caption postingan keempat"));
    }

    public static PostManager getInstance() {
        if (instance == null) instance = new PostManager();
        return instance;
    }

    public void addPost(Post post) {
        posts.add(0, post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public int getCount() {
        return posts.size();
    }
}