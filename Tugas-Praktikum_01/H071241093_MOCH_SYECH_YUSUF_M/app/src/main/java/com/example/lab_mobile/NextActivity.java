package com.example.lab_mobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.imageview.ShapeableImageView;

public class NextActivity extends AppCompatActivity {

    private TextView tvNameDisplay, tvUsernameDisplay, tvBioDisplay;
    private ImageView btnBack, btnChangePhotoIcon;
    private ShapeableImageView ivProfileEdit;
    private TextView tvChangePhoto;
    private LinearLayout rowName, rowUsername, rowBio;
    private Uri selectedImageUri;

    // Launcher untuk memilih foto dari galeri
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivProfileEdit.setImageURI(uri);
                    // Langsung kirim balik ke MainActivity jika ingin instant save
                    updateMainProfile();
                }
            });

    // Launcher untuk mengedit field (Nama, Username, Bio)
    private final ActivityResultLauncher<Intent> editFieldLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String key = result.getData().getStringExtra("field_key");
                    String value = result.getData().getStringExtra("field_value");

                    if ("name".equals(key)) {
                        tvNameDisplay.setText(value);
                    } else if ("username".equals(key)) {
                        tvUsernameDisplay.setText(value);
                    } else if ("bio".equals(key)) {
                        tvBioDisplay.setText(value);
                    }
                    
                    // Update ke MainActivity
                    updateMainProfile();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi View
        tvNameDisplay = findViewById(R.id.tv_name_display);
        tvUsernameDisplay = findViewById(R.id.tv_username_display);
        tvBioDisplay = findViewById(R.id.tv_bio_display);
        btnBack = findViewById(R.id.btn_back);
        ivProfileEdit = findViewById(R.id.iv_profile_edit);
        tvChangePhoto = findViewById(R.id.tv_change_photo);
        btnChangePhotoIcon = findViewById(R.id.btn_change_photo_icon);
        rowName = findViewById(R.id.row_name);
        rowUsername = findViewById(R.id.row_username);
        rowBio = findViewById(R.id.row_bio);

        // Ambil data awal dari Intent
        String currentName = getIntent().getStringExtra("current_name");
        String currentUsername = getIntent().getStringExtra("current_username");
        String currentBio = getIntent().getStringExtra("current_bio");
        String currentImageUriString = getIntent().getStringExtra("current_image_uri");

        tvNameDisplay.setText(currentName);
        tvUsernameDisplay.setText(currentUsername);
        tvBioDisplay.setText(currentBio);
        if (currentImageUriString != null) {
            selectedImageUri = Uri.parse(currentImageUriString);
            ivProfileEdit.setImageURI(selectedImageUri);
        }

        // Klik Baris Nama
        rowName.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditFieldActivity.class);
            intent.putExtra("field_key", "name");
            intent.putExtra("field_title", "Nama");
            intent.putExtra("field_value", tvNameDisplay.getText().toString());
            intent.putExtra("field_helper", "Bantu orang menemukan akun Anda dengan menggunakan nama yang Anda kenal.");
            editFieldLauncher.launch(intent);
        });

        // Klik Baris Username
        rowUsername.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditFieldActivity.class);
            intent.putExtra("field_key", "username");
            intent.putExtra("field_title", "Nama pengguna");
            intent.putExtra("field_value", tvUsernameDisplay.getText().toString());
            intent.putExtra("field_helper", "Anda dapat mengubah nama pengguna kembali ke syechyusufm dalam 14 hari lagi.");
            editFieldLauncher.launch(intent);
        });

        // Klik Baris Bio
        rowBio.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditFieldActivity.class);
            intent.putExtra("field_key", "bio");
            intent.putExtra("field_title", "Bio");
            intent.putExtra("field_value", tvBioDisplay.getText().toString());
            editFieldLauncher.launch(intent);
        });

        // Ganti Foto
        Runnable pickImageRunnable = () -> pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());

        tvChangePhoto.setOnClickListener(v -> pickImageRunnable.run());
        btnChangePhotoIcon.setOnClickListener(v -> pickImageRunnable.run());
        ivProfileEdit.setOnClickListener(v -> pickImageRunnable.run());

        btnBack.setOnClickListener(v -> finish());
    }

    private void updateMainProfile() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", tvNameDisplay.getText().toString());
        resultIntent.putExtra("username", tvUsernameDisplay.getText().toString());
        resultIntent.putExtra("bio", tvBioDisplay.getText().toString());
        if (selectedImageUri != null) {
            resultIntent.putExtra("image_uri", selectedImageUri.toString());
        }
        setResult(RESULT_OK, resultIntent);
    }
}
