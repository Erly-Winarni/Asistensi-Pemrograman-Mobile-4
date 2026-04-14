package com.example.lab_mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PostDetailActivity extends AppCompatActivity {

    private ImageView ivPostImage, btnBack;
    private TextView tvUsernameTitle, tvPostUsername, tvPostCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_detail);

        View root = findViewById(R.id.header).getRootView();
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivPostImage = findViewById(R.id.iv_post_image);
        btnBack = findViewById(R.id.btn_back);
        tvUsernameTitle = findViewById(R.id.tv_username_title);
        tvPostUsername = findViewById(R.id.tv_post_username);
        tvPostCaption = findViewById(R.id.tv_post_caption);

        // Get data from intent
        int imageResId = getIntent().getIntExtra("image_res_id", R.drawable.kucing);
        String username = getIntent().getStringExtra("username");
        String caption = getIntent().getStringExtra("caption");

        // Set data
        ivPostImage.setImageResource(imageResId);
        tvUsernameTitle.setText(username);
        tvPostUsername.setText(username);
        tvPostCaption.setText(username + " " + caption);

        btnBack.setOnClickListener(v -> finish());
    }
}
