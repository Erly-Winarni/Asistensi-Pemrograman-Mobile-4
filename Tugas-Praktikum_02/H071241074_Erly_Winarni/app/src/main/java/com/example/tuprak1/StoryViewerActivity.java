package com.example.tuprak1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StoryViewerActivity extends AppCompatActivity {
    private int[] images;
    private String[] labels;
    private int currentIndex = 0;
    private ObjectAnimator progressAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_story_viewer);

        images       = getIntent().getIntArrayExtra("images");
        labels       = getIntent().getStringArrayExtra("labels");
        currentIndex = getIntent().getIntExtra("start_index", 0);

        if (images == null || images.length == 0) {
            finish();
            return;
        }

        showStory(currentIndex);

        findViewById(R.id.viewTapLeft).setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                showStory(currentIndex);
            } else {
                finish();
            }
        });

        findViewById(R.id.viewTapRight).setOnClickListener(v -> {
            if (currentIndex < images.length - 1) {
                currentIndex++;
                showStory(currentIndex);
            } else {
                finish();
            }
        });

        findViewById(R.id.ivCloseStory).setOnClickListener(v -> finish());
    }

    private void showStory(int index) {
        ((ImageView) findViewById(R.id.ivStoryImage)).setImageResource(images[index]);

        if (labels != null && index < labels.length) {
            ((TextView) findViewById(R.id.tvStoryUsername)).setText(labels[index]);
        }

        startProgress();
    }

    private void startProgress() {
        if (progressAnimator != null) progressAnimator.cancel();

        ProgressBar progressBar = findViewById(R.id.progressStory);
        progressBar.setProgress(0);

        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        progressAnimator.setDuration(5000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (currentIndex < images.length - 1) {
                    currentIndex++;
                    showStory(currentIndex);
                } else {
                    finish();
                }
            }
        });
        progressAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressAnimator != null) progressAnimator.cancel();
    }
}