package com.example.tuprak1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CreatePostActivity extends AppCompatActivity {
    private static final int REQUEST_PICK_IMAGE = 200;
    private static final int REQUEST_PERMISSION = 201;
    private ImageView ivPreview;
    private TextView  tvPickPhoto;
    private EditText  etCaption;
    private String    savedImagePath;
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
        setContentView(R.layout.activity_create_post);

        ivPreview   = findViewById(R.id.ivPreview);
        tvPickPhoto = findViewById(R.id.tvPickPhoto);
        etCaption   = findViewById(R.id.etCaption);

        ivPreview.setOnClickListener(v -> checkPermissionAndOpenGallery());
        tvPickPhoto.setOnClickListener(v -> checkPermissionAndOpenGallery());

        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());

        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> doPost());
    }

    private void checkPermissionAndOpenGallery() {
        String permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        if (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{permission}, REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            Toast.makeText(this, "Izin diperlukan untuk memilih foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE
                && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {

            Uri selectedUri = data.getData();

            savedImagePath = copyImageToInternalStorage(selectedUri);

            if (savedImagePath != null) {
                Bitmap bmp = BitmapFactory.decodeFile(savedImagePath);
                ivPreview.setImageBitmap(bmp);
                tvPickPhoto.setText("Ganti foto");
            } else {
                Toast.makeText(this, "Gagal memuat foto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String copyImageToInternalStorage(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if (inputStream == null) return null;

            File dir = new File(getFilesDir(), "posts");
            if (!dir.exists()) dir.mkdirs();

            String fileName = "post_" + System.currentTimeMillis() + ".jpg";
            File outFile = new File(dir, fileName);

            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();
            inputStream.close();

            return outFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void doPost() {
        if (savedImagePath == null) {
            Toast.makeText(this, "Pilih foto dulu!", Toast.LENGTH_SHORT).show();
            return;
        }
        String caption = etCaption.getText().toString().trim();
        PostManager.getInstance().addPost(
                new PostManager.Post(savedImagePath, caption));
        setResult(Activity.RESULT_OK);
        Toast.makeText(this, "Postingan berhasil dibagikan!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
