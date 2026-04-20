package com.example.tuprak1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT_PROFILE = 100;
    private static final int REQUEST_CREATE_POST  = 101;

    private TextView   tvFullName, tvUsername, tvBio, tvPronouns, tvLink, tvBanner, tvMusic;
    private TextView   tvPostCount;
    private GridLayout gridPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvFullName  = findViewById(R.id.tvFullName);
        tvUsername  = findViewById(R.id.tvUsername);
        tvBio       = findViewById(R.id.tvBio);
        tvPronouns  = findViewById(R.id.tvPronouns);
        tvLink      = findViewById(R.id.tvLink);
        tvBanner    = findViewById(R.id.tvBanner);
        tvMusic     = findViewById(R.id.tvMusic);
        tvPostCount = findViewById(R.id.tvPostCount);
        gridPosts   = findViewById(R.id.gridPosts);

        Button btnEditProfile = findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("name",     tvFullName.getText().toString());
            intent.putExtra("username", tvUsername.getText().toString());
            intent.putExtra("bio",      tvBio.getText().toString());
            intent.putExtra("pronouns", tvPronouns != null ? tvPronouns.getText().toString() : "");
            intent.putExtra("link",     tvLink != null ? tvLink.getText().toString() : "");
            intent.putExtra("banner",   tvBanner != null ? tvBanner.getText().toString() : "");
            intent.putExtra("music",    tvMusic != null ? tvMusic.getText().toString() : "");
            startActivityForResult(intent, REQUEST_EDIT_PROFILE);
        });

        ImageView ivAddPost = findViewById(R.id.ivAddPost);
        ivAddPost.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, CreatePostActivity.class);
            startActivityForResult(intent, REQUEST_CREATE_POST);
        });

        findViewById(R.id.layoutHighlight1).setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, StoryViewerActivity.class);
            intent.putExtra("start_index", 0);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        findViewById(R.id.layoutHighlight2).setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, StoryViewerActivity.class);
            intent.putExtra("start_index", 1);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        ImageView ivHome = findViewById(R.id.ivHome);
        ivHome.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        refreshPostGrid();
    }

    private void refreshPostGrid() {
        gridPosts.removeAllViews();

        List<PostManager.Post> posts = PostManager.getInstance().getPosts();
        tvPostCount.setText(String.valueOf(posts.size()));

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int cellSize    = screenWidth / 3;

        for (PostManager.Post post : posts) {
            ImageView iv = new ImageView(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width  = cellSize;
            params.height = cellSize;
            params.setMargins(1, 1, 1, 1);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            if (post.isFile) {
                Bitmap bmp = BitmapFactory.decodeFile(post.imagePath);
                if (bmp != null) iv.setImageBitmap(bmp);
            } else {
                iv.setImageResource(post.imageRes);
            }

            final PostManager.Post finalPost = post;
            iv.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, PostDetailActivity.class);
                if (finalPost.isFile) {
                    intent.putExtra("post_image_path", finalPost.imagePath);
                } else {
                    intent.putExtra("post_image", finalPost.imageRes);
                }
                intent.putExtra("caption", finalPost.caption);
                startActivity(intent);
            });

            gridPosts.addView(iv);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CREATE_POST && resultCode == RESULT_OK) {
            refreshPostGrid();
        }

        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {
            String newName     = data.getStringExtra("name");
            String newUsername = data.getStringExtra("username");
            String newBio      = data.getStringExtra("bio");
            String newPronouns = data.getStringExtra("pronouns");
            String newLink     = data.getStringExtra("link");
            String newBanner   = data.getStringExtra("banner");
            String newMusic    = data.getStringExtra("music");

            if (newName != null && !newName.isEmpty())         tvFullName.setText(newName);
            if (newUsername != null && !newUsername.isEmpty()) tvUsername.setText(newUsername);
            if (newBio != null)      tvBio.setText(newBio);
            if (newPronouns != null) tvPronouns.setText(newPronouns);
            if (newLink != null)     tvLink.setText(newLink);
            if (newBanner != null)   tvBanner.setText(newBanner);
            if (newMusic != null)    tvMusic.setText(newMusic);
        }
    }
}