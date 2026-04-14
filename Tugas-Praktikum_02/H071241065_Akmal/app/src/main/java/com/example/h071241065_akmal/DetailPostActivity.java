package com.example.h071241065_akmal;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.h071241065_akmal.model.Post;

public class DetailPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        ImageView ivPostImage = findViewById(R.id.iv_detail_post_image);
        TextView tvUsername = findViewById(R.id.tv_detail_username);
        TextView tvCaption = findViewById(R.id.tv_detail_caption);
        TextView tvBack = findViewById(R.id.tv_back_detail);

        tvBack.setOnClickListener(v -> finish());

        // Menerima data yang dilempar dari MainActivity atau ProfileActivity
        Post post = (Post) getIntent().getSerializableExtra("EXTRA_POST");

        // Memasang data ke tampilan
        if (post != null) {
            if (post.getImageUri() != null) {
                ivPostImage.setImageURI(android.net.Uri.parse(post.getImageUri()));
            } else {
                ivPostImage.setImageResource(post.getPostImageResId());
            }
            tvUsername.setText(post.getUsername());
            tvCaption.setText(post.getCaption());
        }
    }
}