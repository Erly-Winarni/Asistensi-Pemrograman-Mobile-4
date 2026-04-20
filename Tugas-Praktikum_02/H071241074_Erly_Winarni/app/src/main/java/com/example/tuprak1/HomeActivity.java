package com.example.tuprak1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvFeed = findViewById(R.id.rvFeed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        List<FeedItem> feedList = getDummyData();
        FeedAdapter adapter = new FeedAdapter(this, feedList);
        rvFeed.setAdapter(adapter);

        RecyclerView rvStories = findViewById(R.id.rvStories);
        rvStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        StoryAdapter storyAdapter = new StoryAdapter(this, feedList);
        rvStories.setAdapter(storyAdapter);

        de.hdodenhof.circleimageview.CircleImageView ivProfile = findViewById(R.id.ivProfile);

        ivProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    private List<FeedItem> getDummyData() {
        List<FeedItem> list = new ArrayList<>();

        list.add(new FeedItem(
                "rlyy.zz", "Makassar", "SEVENTEENAGERS!!!", "2 jam lalu", 1234,
                R.drawable.ic_avatar, R.drawable.f1,
                "Erly Winarni", "ini bio kah", "she", 430, 327, 4,
                new int[]{R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f5, R.drawable.h1}),
                        new HighlightItem("porto", new int[]{R.drawable.f6, R.drawable.f6, R.drawable.f7}),
                        new HighlightItem("when yh", new int[]{R.drawable.f9, R.drawable.h1, R.drawable.h2}),
                        new HighlightItem("itu", new int[]{R.drawable.f8, R.drawable.f4, R.drawable.f9}),
                        new HighlightItem("lagi", new int[]{R.drawable.f10, R.drawable.f2, R.drawable.f3}),
                        new HighlightItem("lab", new int[]{R.drawable.f3, R.drawable.f10, R.drawable.f2})
                }));

        list.add(new FeedItem(
                "aosun", "Panakukang", "sisfo rawr", "3 jam lalu", 856,
                R.drawable.a1, R.drawable.f2,
                "Aosun", "ini bio 2", "she", 210, 180, 7,
                new int[]{R.drawable.f2, R.drawable.f5, R.drawable.f6},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));

        list.add(new FeedItem(
                "erlywin", "Soppeng", "prom nightzzz", "5 jam lalu", 2341,
                R.drawable.a2, R.drawable.f3,
                "Erly Win", "nantuk", "she", 1200, 400, 15,
                new int[]{R.drawable.f7, R.drawable.f8, R.drawable.f9, R.drawable.f2},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));

        list.add(new FeedItem(
                "user_anu", "Trans Studio Mall", "suaa", "6 jam lalu", 432,
                R.drawable.a4, R.drawable.f4,
                "User", "yey", "he", 98, 120, 3,
                new int[]{R.drawable.f10, R.drawable.f5},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));


        list.add(new FeedItem(
                "catz", "Sahabat", "mikir kidz", "8 jam lalu", 5678,
                R.drawable.a5, R.drawable.f5,
                "miaw miaw", "miaw", "he", 8900, 300, 42,
                new int[]{R.drawable.f5, R.drawable.f2, R.drawable.f9},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));

        list.add(new FeedItem(
                "daily.ly", "Makassar", "porto buriq euy", "10 jam lalu", 321,
                R.drawable.a6, R.drawable.f6,
                "Desain", "desain", "she", 560, 230, 9,
                new int[]{R.drawable.f2, R.drawable.f7, R.drawable.f8},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));

        list.add(new FeedItem(
                "erly30", "The Rinra", "turu", "12 jam lalu", 789,
                R.drawable.ic_avatar, R.drawable.f7,
                "Erly Lagi", "turu lovers", "she", 340, 210, 11,
                new int[]{R.drawable.f10, R.drawable.f3, R.drawable.f2},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));


        list.add(new FeedItem(
                "wahyu.23", "Sinjai", "IWAKKK", "14 jam lalu", 1023,
                R.drawable.a8, R.drawable.f8,
                "Wahyu", "iwak peyek", "he", 670, 450, 6,
                new int[]{R.drawable.f2, R.drawable.f7},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));

        list.add(new FeedItem(
                "ly_mks", "Lab DOP", "ngemper dpn dop", "1 hari lalu", 654,
                R.drawable.a9, R.drawable.f9,
                "ly", "anu", "she", 230, 198, 8,
                new int[]{R.drawable.f1, R.drawable.f3, R.drawable.f9},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));

        list.add(new FeedItem(
                "tido", "Tamalanrea", "tido pun sedap", "1 hari lalu", 4321,
                R.drawable.a10, R.drawable.f10,
                "Tido", "yeuh", "he", 12000, 500, 78,
                new int[]{R.drawable.f10, R.drawable.f9, R.drawable.f8, R.drawable.f4},
                new HighlightItem[]{
                        new HighlightItem("UH",  new int[]{R.drawable.h1, R.drawable.h2, R.drawable.f1}),
                        new HighlightItem("art", new int[]{R.drawable.h2, R.drawable.f2, R.drawable.f3, R.drawable.h1}),
                        new HighlightItem("shs", new int[]{R.drawable.f4, R.drawable.h1})
                }));
        return list;
    }
}