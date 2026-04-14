package com.example.lab_mobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    // Deklarasi view yang akan diubah datanya
    private TextView tvName, tvBio, tvUsernameTop, tvHandle;
    private Button btnEditProfile;
    private ShapeableImageView ivProfileMain;
    private ImageView ivPost1, ivPost2, ivPost3;
    private Uri currentProfileImageUri;

    // Launcher untuk menerima data kembali dari Edit Profile (NextActivity)
    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Ambil data yang dikirim balik
                    String newName = result.getData().getStringExtra("name");
                    String newUsername = result.getData().getStringExtra("username");
                    String newBio = result.getData().getStringExtra("bio");
                    String newImageUriString = result.getData().getStringExtra("image_uri");

                    // Update tampilan dengan data baru
                    tvName.setText(newName);
                    tvBio.setText(newBio);
                    tvUsernameTop.setText(newUsername);
                    tvHandle.setText("@" + newUsername);
                    
                    if (newImageUriString != null) {
                        currentProfileImageUri = Uri.parse(newImageUriString);
                        ivProfileMain.setImageURI(currentProfileImageUri);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Menangani padding sistem (status bar/navigasi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi View
        tvName = findViewById(R.id.tv_name);
        tvBio = findViewById(R.id.tv_bio);
        tvUsernameTop = findViewById(R.id.tv_username_top);
        tvHandle = findViewById(R.id.tv_handle);
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        ivProfileMain = findViewById(R.id.iv_profile_main);
        ivPost1 = findViewById(R.id.iv_post_1);
        ivPost2 = findViewById(R.id.iv_post_2);
        ivPost3 = findViewById(R.id.iv_post_3);

        // Klik tombol Edit Profil
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NextActivity.class);
            
            // Kirim data saat ini ke halaman edit agar bisa diubah
            intent.putExtra("current_name", tvName.getText().toString());
            intent.putExtra("current_username", tvUsernameTop.getText().toString());
            intent.putExtra("current_bio", tvBio.getText().toString());
            if (currentProfileImageUri != null) {
                intent.putExtra("current_image_uri", currentProfileImageUri.toString());
            }
            
            editProfileLauncher.launch(intent);
        });

        // Setup Post Click Listeners
        setupPostClick(ivPost1, R.drawable.kucing, "Mengabadikan sebuah moment itu penting, kenapa? karena di setiap potretan ada kenangan yang tidak bisa kita ulang kembali.");
        setupPostClick(ivPost2, R.drawable.nadil, "Kesempatan tidak datang dua kali.");
        setupPostClick(ivPost3, R.drawable.profil, "Profil saya.");
    }

    private void setupPostClick(ImageView iv, int resId, String caption) {
        iv.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PostDetailActivity.class);
            intent.putExtra("image_res_id", resId);
            intent.putExtra("username", tvUsernameTop.getText().toString());
            intent.putExtra("caption", caption);
            startActivity(intent);
        });
    }
}
