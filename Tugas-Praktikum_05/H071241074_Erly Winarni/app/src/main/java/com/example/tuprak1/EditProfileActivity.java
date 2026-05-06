package com.example.tuprak1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class EditProfileActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etUsernameEdit;
    private EditText etBio;
    private ImageView btnBack;
    private TextView btnSave;
    private EditText etGender;
    private EditText etPronouns;
    private EditText etLink;
    private EditText etBanner;
    private EditText etMusic;
    private PreferenceManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefManager = new PreferenceManager(this);
        if (prefManager.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etName         = findViewById(R.id.etName);
        etUsernameEdit = findViewById(R.id.etUsernameEdit);
        etBio          = findViewById(R.id.etBio);
        btnBack        = findViewById(R.id.btnBack);
        btnSave        = findViewById(R.id.btnSave);
        etGender       = findViewById(R.id.etGender);
        etPronouns     = findViewById(R.id.etPronouns);
        etLink         = findViewById(R.id.etLink);
        etBanner       = findViewById(R.id.etBanner);
        etMusic        = findViewById(R.id.etMusic);

        Intent incoming = getIntent();
        if (incoming != null) {
            String name     = incoming.getStringExtra("name");
            String username = incoming.getStringExtra("username");
            String bio      = incoming.getStringExtra("bio");
            String gender   = incoming.getStringExtra("gender");
            String pronouns = incoming.getStringExtra("pronouns");
            String link     = incoming.getStringExtra("link");
            String banner   = incoming.getStringExtra("banner");
            String music    = incoming.getStringExtra("music");

            if (name     != null) etName.setText(name);
            if (username != null) etUsernameEdit.setText(username);
            if (bio      != null) etBio.setText(bio);
            if (gender   != null) etGender.setText(gender);
            if (pronouns != null) etPronouns.setText(pronouns);
            if (link     != null) etLink.setText(link);
            if (banner   != null) etBanner.setText(banner);
            if (music    != null) etMusic.setText(music);
        }

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> saveAndReturn());
    }

    private void saveAndReturn() {
        String newName     = etName.getText().toString().trim();
        String newUsername = etUsernameEdit.getText().toString().trim();
        String newBio      = etBio.getText().toString().trim();
        String newGender   = etGender.getText().toString().trim();
        String newPronouns = etPronouns.getText().toString().trim();
        String newLink     = etLink.getText().toString().trim();
        String newBanner   = etBanner.getText().toString().trim();
        String newMusic    = etMusic.getText().toString().trim();

        if (newName.isEmpty()) {
            etName.setError("Nama tidak boleh kosong");
            etName.requestFocus();
            return;
        }
        if (newUsername.isEmpty()) {
            etUsernameEdit.setError("Nama pengguna tidak boleh kosong");
            etUsernameEdit.requestFocus();
            return;
        }

        Intent result = new Intent();
        result.putExtra("name",     newName);
        result.putExtra("username", newUsername);
        result.putExtra("bio",      newBio);
        result.putExtra("gender", newGender);
        result.putExtra("pronouns", newPronouns);
        result.putExtra("link", newLink);
        result.putExtra("banner", newBanner);
        result.putExtra("music", newMusic);

        setResult(Activity.RESULT_OK, result);
        Toast.makeText(this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
        finish();
    }
}
