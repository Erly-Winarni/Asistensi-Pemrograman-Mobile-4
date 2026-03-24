package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private Uri selectedImageUri = null;
    private ImageView imgProfileEdit;

    private final ActivityResultLauncher<String> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    imgProfileEdit.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditText etName = findViewById(R.id.etName);
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etBio = findViewById(R.id.etBio);
        TextView btnSave = findViewById(R.id.btnSave);
        ImageView btnCancel = findViewById(R.id.btnCancel);

        imgProfileEdit = findViewById(R.id.imgProfileEdit);
        TextView tvChangePhoto = findViewById(R.id.tvChangePhoto);

        Intent intent = getIntent();
        String currentName = intent.getStringExtra("CURRENT_NAME");
        String currentUsername = intent.getStringExtra("CURRENT_USERNAME");
        String currentBio = intent.getStringExtra("CURRENT_BIO");

        String photoUriString = intent.getStringExtra("CURRENT_PHOTO_URI");

        if (photoUriString != null) {
            selectedImageUri = Uri.parse(photoUriString);
            imgProfileEdit.setImageURI(selectedImageUri);
        }

        if (currentName != null) etName.setText(currentName);
        if (currentUsername != null) etUsername.setText(currentUsername);
        if (currentBio != null) etBio.setText(currentBio);

        tvChangePhoto.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        imgProfileEdit.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        btnCancel.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("EXTRA_NAME", etName.getText().toString());
            resultIntent.putExtra("EXTRA_USERNAME", etUsername.getText().toString());
            resultIntent.putExtra("EXTRA_BIO", etBio.getText().toString());

            if (selectedImageUri != null) {
                resultIntent.setData(selectedImageUri);
                resultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}