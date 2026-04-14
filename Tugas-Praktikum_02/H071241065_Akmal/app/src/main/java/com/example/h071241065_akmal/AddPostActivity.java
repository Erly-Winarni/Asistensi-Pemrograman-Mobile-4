package com.example.h071241065_akmal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.h071241065_akmal.model.DataSource;
import com.example.h071241065_akmal.model.Post;

public class AddPostActivity extends AppCompatActivity {

    private ImageView ivUploadImage;
    private Uri selectedImageUri = null; // Menyimpan alamat gambar yang dipilih

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        ivUploadImage = findViewById(R.id.iv_upload_image);
        EditText etCaption = findViewById(R.id.et_caption);
        Button btnUpload = findViewById(R.id.btn_upload);

        //  penangkap hasil dari galeri
        ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        ivUploadImage.setImageURI(selectedImageUri);
                    }
                }
        );

        // Buka galeri saat area gambar diklik
        ivUploadImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

        // Aksi saat tombol upload diklik
        btnUpload.setOnClickListener(v -> {
            String captionText = etCaption.getText().toString();

            if (selectedImageUri == null) {
                Toast.makeText(this, "Pilih gambar dari galeri dulu!", Toast.LENGTH_SHORT).show();
            } else if (captionText.isEmpty()) {
                Toast.makeText(this, "Caption tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else {
                Post newPost = new Post(
                        android.R.drawable.sym_def_app_icon,
                        "akmal_065",
                        selectedImageUri.toString(),
                        captionText
                );

                // Masukkan data ke urutan pertama
                DataSource.profilePosts.add(0, newPost);

                Toast.makeText(this, "Postingan berhasil diupload!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}