package com.example.myapplication;

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

    private TextView tvName;
    private TextView tvUsername;
    private TextView tvBio;
    private ImageView imgProfile;
    private Uri currentProfileUri = null;

    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String newName = data.getStringExtra("EXTRA_NAME");
                        String newUsername = data.getStringExtra("EXTRA_USERNAME");
                        String newBio = data.getStringExtra("EXTRA_BIO");

                        Uri newImageUri = data.getData();

                        if (newName != null) tvName.setText(newName);
                        if (newUsername != null) tvUsername.setText(newUsername);
                        if (newBio != null) tvBio.setText(newBio);

                        if (newImageUri != null) {
                            imgProfile.setImageURI(newImageUri);
                            currentProfileUri = newImageUri;
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsernameTop);
        tvBio = findViewById(R.id.tvBio);
        imgProfile = findViewById(R.id.imgProfile);
        Button btnEditProfile = findViewById(R.id.btnEditProfile);

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
            intent.putExtra("CURRENT_NAME", tvName.getText().toString());
            intent.putExtra("CURRENT_USERNAME", tvUsername.getText().toString());
            intent.putExtra("CURRENT_BIO", tvBio.getText().toString());

            if (currentProfileUri != null) {
                intent.putExtra("CURRENT_PHOTO_URI", currentProfileUri.toString());
            }

            editProfileLauncher.launch(intent);
        });
    }
}