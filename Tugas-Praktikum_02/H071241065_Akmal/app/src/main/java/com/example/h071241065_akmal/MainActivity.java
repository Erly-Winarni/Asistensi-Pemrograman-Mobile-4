package com.example.h071241065_akmal;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071241065_akmal.model.DataSource;
import com.example.h071241065_akmal.model.Post;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHomePosts;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Panggil Data Dummy (hanya dipanggil sekali)
        DataSource.generateDummyData();

        // Hubungkan RecyclerView dari XML
        rvHomePosts = findViewById(R.id.rv_home_posts);
        rvHomePosts.setLayoutManager(new LinearLayoutManager(this));

        // Pasang Adapter dengan data "homePosts" dari DataSource
        postAdapter = new PostAdapter(DataSource.homePosts);
        rvHomePosts.setAdapter(postAdapter);

        // Tangani event klik menggunakan Intent
        postAdapter.setOnItemClickCallback(new PostAdapter.OnItemClickCallback() {
            @Override
            public void onProfileClicked(Post data) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("EXTRA_USER", data);
                startActivity(intent);
            }

            @Override
            public void onPostClicked(Post data) {
                // Berpindah ke Halaman Detail Post saat gambar diklik
                Intent intent = new Intent(MainActivity.this, DetailPostActivity.class);
                intent.putExtra("EXTRA_POST", data);
                startActivity(intent);
            }
        });
    }
}