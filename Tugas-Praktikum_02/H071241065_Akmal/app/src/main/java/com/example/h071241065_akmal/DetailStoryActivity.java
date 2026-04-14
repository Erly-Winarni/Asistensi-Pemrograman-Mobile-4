package com.example.h071241065_akmal;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);

        TextView tvStoryTitle = findViewById(R.id.tv_story_title);
        ImageView ivStoryImage = findViewById(R.id.iv_story_image); // Panggil ImageView-nya

        // Menerima judul story
        String title = getIntent().getStringExtra("EXTRA_STORY_TITLE");
        if (title != null) {
            tvStoryTitle.setText(title);
        }

        // Menerima dan memasang gambar story
        // Angka 0 di bawah adalah nilai default/cadangan jika gambar gagal dimuat
        int imageResId = getIntent().getIntExtra("EXTRA_STORY_IMAGE", 0);
        if (imageResId != 0) {
            ivStoryImage.setImageResource(imageResId);
        }

        // Otomatis tertutup setelah 3 detik
        new Handler().postDelayed(this::finish, 3000);
    }
}