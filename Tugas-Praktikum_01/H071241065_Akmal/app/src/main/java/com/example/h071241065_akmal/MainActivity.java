package com.example.h071241065_akmal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvName, tvUsername, tvBio;
    private Button btnEditProfile;
    private ImageView ivProfile;

    // 1. Daftarkan launcher untuk menerima hasil dari EditProfileActivity
    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();

                    // Ambil data teks dari Intent
                    String updatedName = data.getStringExtra("EXTRA_NAME");
                    String updatedUsername = data.getStringExtra("EXTRA_USERNAME");
                    String updatedBio = data.getStringExtra("EXTRA_BIO");

                    // Update UI teks dengan data baru
                    tvName.setText(updatedName);
                    tvUsername.setText(updatedUsername);
                    tvBio.setText(updatedBio);

                    // Pindahkan kode penangkap gambar ke sini!
                    String updatedImageUriString = data.getStringExtra("EXTRA_IMAGE_URI");
                    if (updatedImageUriString != null) {
                        Uri updatedImageUri = Uri.parse(updatedImageUriString);
                        ivProfile.setImageURI(updatedImageUri);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sambungkan semua ID dari XML ke Java
        tvName = findViewById(R.id.tv_name);
        tvUsername = findViewById(R.id.tv_username);
        tvBio = findViewById(R.id.tv_bio);
        btnEditProfile = findViewById(R.id.btn_edit_profile);

        // JANGAN LUPA: Sambungkan ImageView fotonya juga
        ivProfile = findViewById(R.id.iv_profile);

        btnEditProfile.setOnClickListener(v -> {
            // 2. Buat intent untuk pindah ke halaman Edit
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);

            // Opsional: Kirim data saat ini ke halaman Edit agar form sudah terisi
            intent.putExtra("CURRENT_NAME", tvName.getText().toString());
            intent.putExtra("CURRENT_USERNAME", tvUsername.getText().toString());
            intent.putExtra("CURRENT_BIO", tvBio.getText().toString());

            // 3. Jalankan Intent menggunakan launcher
            editProfileLauncher.launch(intent);
        });
    }
}