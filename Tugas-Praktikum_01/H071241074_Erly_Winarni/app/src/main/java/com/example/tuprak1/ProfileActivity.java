package com.example.tuprak1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private static final int REQUEST_EDIT_PROFILE = 100;
    private TextView tvFullName;
    private TextView tvUsername;
    private TextView tvBio;
    private TextView tvPronouns;
    private TextView tvLink;
    private TextView tvBanner;
    private TextView tvMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvFullName = findViewById(R.id.tvFullName);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio      = findViewById(R.id.tvBio);
        Button btnEditProfile = findViewById(R.id.btnEditProfile);
        tvPronouns = findViewById(R.id.tvPronouns);
        tvLink     = findViewById(R.id.tvLink);
        tvBanner   = findViewById(R.id.tvBanner);
        tvMusic    = findViewById(R.id.tvMusic);

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {
            String newName     = data.getStringExtra("name");
            String newUsername = data.getStringExtra("username");
            String newBio      = data.getStringExtra("bio");
            String newPronouns = data.getStringExtra("pronouns");
            String newLink     = data.getStringExtra("link");
            String newBanner   = data.getStringExtra("banner");
            String newMusic    = data.getStringExtra("music");

            if (newName != null && !newName.isEmpty()) {
                tvFullName.setText(newName);
            }
            if (newUsername != null && !newUsername.isEmpty()) {
                tvUsername.setText(newUsername);
            }
            if (newBio != null) {
                tvBio.setText(newBio);
            }
            if (newPronouns != null) {
                tvPronouns.setText(newPronouns);
            }
            if (newLink != null) {
                tvLink.setText(newLink);
            }
            if (newBanner != null) {
                tvBanner.setText(newBanner);
            }
            if (newMusic != null) {
                tvMusic.setText(newMusic);
            }
        }
    }
}