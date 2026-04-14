package com.example.h071241065_akmal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071241065_akmal.model.DataSource;
import com.example.h071241065_akmal.model.Post;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView rvHighlights;
    private RecyclerView rvProfilePosts;
    private HighlightAdapter highlightAdapter;
    private PostAdapter postAdapter;
    private ArrayList<Post> displayPosts = new ArrayList<>(); // List sementara untuk menampung feed

    // Penanda untuk mengecek apakah ini profil kita atau bukan
    private boolean isMyProfile = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView ivProfileHeader = findViewById(R.id.iv_profile_header);
        TextView tvUsernameHeader = findViewById(R.id.tv_username_header);
        Button btnAddPost = findViewById(R.id.btn_add_post);

        // Tangkap data user yang diklik dari Home
        Post clickedUser = (Post) getIntent().getSerializableExtra("EXTRA_USER");

        if (clickedUser != null) {
            tvUsernameHeader.setText(clickedUser.getUsername());

            if (clickedUser.getImageUri() != null) {
                ivProfileHeader.setImageURI(android.net.Uri.parse(clickedUser.getImageUri()));
            } else {
                ivProfileHeader.setImageResource(clickedUser.getProfileImageResId());
            }

            if (!clickedUser.getUsername().equals("akmal_065")) {
                isMyProfile = false;

                btnAddPost.setVisibility(View.GONE);

                displayPosts.clear();
                for (Post post : DataSource.homePosts) {
                    if (post.getUsername().equals(clickedUser.getUsername())) {
                        displayPosts.add(post);
                    }
                }
            } else {
                isMyProfile = true; // Set flag kembali ke true
                btnAddPost.setVisibility(View.VISIBLE);
                displayPosts.clear();
                displayPosts.addAll(DataSource.profilePosts);
            }
        } else {
            // Jika tidak ada data yang diklik
            isMyProfile = true;
            btnAddPost.setVisibility(View.VISIBLE);
            displayPosts.clear();
            displayPosts.addAll(DataSource.profilePosts);
        }

        // Setup RecyclerView Highlights
        rvHighlights = findViewById(R.id.rv_highlights);
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        highlightAdapter = new HighlightAdapter(DataSource.highlights);
        rvHighlights.setAdapter(highlightAdapter);

        // Setup RecyclerView Posts menggunakan "displayPosts" yang sudah disaring
        rvProfilePosts = findViewById(R.id.rv_profile_posts);
        rvProfilePosts.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(displayPosts);
        rvProfilePosts.setAdapter(postAdapter);

        // Aksi Tombol Tambah Postingan
        btnAddPost.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AddPostActivity.class);
            startActivity(intent);
        });

        // Aksi Klik Highlights
        highlightAdapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(ProfileActivity.this, DetailStoryActivity.class);
            intent.putExtra("EXTRA_STORY_TITLE", data.getTitle());
            intent.putExtra("EXTRA_STORY_IMAGE", data.getCoverImageResId());
            startActivity(intent);
        });

        // Aksi Klik Gambar Postingan di Profil
        postAdapter.setOnItemClickCallback(new PostAdapter.OnItemClickCallback() {
            @Override
            public void onProfileClicked(Post data) { }

            @Override
            public void onPostClicked(Post data) {
                Intent intent = new Intent(ProfileActivity.this, DetailPostActivity.class);
                intent.putExtra("EXTRA_POST", data);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (postAdapter != null && isMyProfile) {
            displayPosts.clear();
            displayPosts.addAll(DataSource.profilePosts);
            postAdapter.notifyDataSetChanged();
        }
    }
}