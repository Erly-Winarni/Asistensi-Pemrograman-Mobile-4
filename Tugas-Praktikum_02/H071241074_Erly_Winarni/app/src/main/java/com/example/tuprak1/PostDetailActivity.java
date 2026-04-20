package com.example.tuprak1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        ImageView ivPostDetail  = findViewById(R.id.ivPostDetail);
        TextView  tvCaption     = findViewById(R.id.tvCaption);
        String caption       = getIntent().getStringExtra("caption");
        String postImagePath = getIntent().getStringExtra("post_image_path");

        if (caption == null) caption = "";
        tvCaption.setText("rlyy.zz  " + caption);

        if (postImagePath != null) {
            Bitmap bmp = BitmapFactory.decodeFile(postImagePath);
            if (bmp != null) {
                ivPostDetail.setImageBitmap(bmp);
            }
        } else {
            int postImage = getIntent().getIntExtra("post_image", R.drawable.f1);
            ivPostDetail.setImageResource(postImage);
        }

        findViewById(R.id.ivBack).setOnClickListener(v -> finish());
    }
}