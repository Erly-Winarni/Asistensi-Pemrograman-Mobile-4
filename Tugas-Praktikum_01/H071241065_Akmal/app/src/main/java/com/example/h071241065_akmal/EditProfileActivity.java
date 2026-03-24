package com.example.h071241065_akmal;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etName, etUsername, etBio;
    private Button btnSave;
    private ImageView ivEditProfile;
    private Uri selectedImageUri = null; // Menyimpan foto yang dipilih

    // Launcher untuk membuka Galeri
    private final ActivityResultLauncher<String> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri; // Simpan URI-nya
                    ivEditProfile.setImageURI(uri); // Tampilkan foto yang dipilih di halaman edit
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // 1. Sambungkan ID XML ke Java
        etName = findViewById(R.id.et_name);
        etUsername = findViewById(R.id.et_username);
        etBio = findViewById(R.id.et_bio);
        btnSave = findViewById(R.id.btn_save);
        ivEditProfile = findViewById(R.id.iv_edit_profile);

        // ==========================================
        // 2. TAMBAHKAN KODE INI UNTUK MENGISI FORM
        // ==========================================
        Intent intent = getIntent();
        if (intent != null) {
            String currentName = intent.getStringExtra("CURRENT_NAME");
            String currentUsername = intent.getStringExtra("CURRENT_USERNAME");
            String currentBio = intent.getStringExtra("CURRENT_BIO");

            // Masukkan teks ke dalam EditText
            etName.setText(currentName);
            etUsername.setText(currentUsername);
            etBio.setText(currentBio);
        }
        // ==========================================

        // 3. Logika klik foto (Buka Galeri)
        ivEditProfile.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        // 4. Logika tombol simpan
        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("EXTRA_NAME", etName.getText().toString());
            resultIntent.putExtra("EXTRA_USERNAME", etUsername.getText().toString());
            resultIntent.putExtra("EXTRA_BIO", etBio.getText().toString());

            if (selectedImageUri != null) {
                resultIntent.putExtra("EXTRA_IMAGE_URI", selectedImageUri.toString());
            }

            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}