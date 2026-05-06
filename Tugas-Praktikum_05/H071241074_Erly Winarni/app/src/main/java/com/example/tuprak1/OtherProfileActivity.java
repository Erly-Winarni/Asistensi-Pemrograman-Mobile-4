package com.example.tuprak1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class OtherProfileActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_other_profile);

        String username = getIntent().getStringExtra("username");
        String fullName = getIntent().getStringExtra("fullName");
        String bio = getIntent().getStringExtra("bio");
        String pronouns = getIntent().getStringExtra("pronouns");
        int followers = getIntent().getIntExtra("followers", 0);
        int following = getIntent().getIntExtra("following", 0);
        int postCount = getIntent().getIntExtra("postCount", 0);
        int avatarRes = getIntent().getIntExtra("avatarRes", 0);
        int[] postImages = getIntent().getIntArrayExtra("postImages");

        String[] hlLabels    = getIntent().getStringArrayExtra("hlLabels");
        int[]    hlSizes     = getIntent().getIntArrayExtra("hlSizes");
        int[]    hlAllImages = getIntent().getIntArrayExtra("hlAllImages");

        List<HighlightItem> highlightList = new ArrayList<>();
        if (hlLabels != null && hlSizes != null && hlAllImages != null) {
            int idx = 0;
            for (int i = 0; i < hlLabels.length; i++) {
                int size = hlSizes[i];
                int[] imgs = new int[size];
                for (int j = 0; j < size; j++) {
                    imgs[j] = hlAllImages[idx++];
                }
                highlightList.add(new HighlightItem(hlLabels[i], imgs));
            }
        }

        RecyclerView rvHighlight = findViewById(R.id.rvHighlight);
        rvHighlight.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHighlight.setAdapter(new HighlightAdapter(this, highlightList));

        ((TextView)  findViewById(R.id.tvUsername)).setText(username);
        ((TextView)  findViewById(R.id.tvFullName)).setText(fullName);
        ((TextView)  findViewById(R.id.tvPronouns)).setText(pronouns);
        ((TextView)  findViewById(R.id.tvBio)).setText(bio);
        ((TextView)  findViewById(R.id.tvPostCount)).setText(String.valueOf(postCount));
        ((TextView)  findViewById(R.id.tvFollowersCount)).setText(String.valueOf(followers));
        ((TextView)  findViewById(R.id.tvFollowingCount)).setText(String.valueOf(following));
        ((ImageView) findViewById(R.id.ivProfilePhoto)).setImageResource(avatarRes);

        findViewById(R.id.ivBack).setOnClickListener(v -> finish());

        List<Integer> imageList = new ArrayList<>();
        if (postImages != null) {
            for (int img : postImages) {
                imageList.add(img);
            }
        }

        RecyclerView rvPostGrid = findViewById(R.id.rvPostGrid);
        rvPostGrid.setLayoutManager(new GridLayoutManager(this, 3));
        rvPostGrid.setAdapter(new PostGridAdapter(this, imageList));
    }
}
