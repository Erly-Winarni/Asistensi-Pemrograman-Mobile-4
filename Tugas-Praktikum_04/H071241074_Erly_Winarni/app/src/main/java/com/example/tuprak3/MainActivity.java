package com.example.tuprak3;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tuprak3.R;
import com.example.tuprak3.AddBookFragment;
import com.example.tuprak3.DetailFragment;
import com.example.tuprak3.FavoritesFragment;
import com.example.tuprak3.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private View detailFragmentContainer;
    private View verticalDivider;
    private View heroMascot;
    private View heroSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNavigationView);
        detailFragmentContainer = findViewById(R.id.detailFragmentContainer);
        verticalDivider = findViewById(R.id.verticalDivider);
        heroMascot = findViewById(R.id.heroMascot);
        heroSubtitle = findViewById(R.id.heroSubtitle);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            closeDetailPanel();
            
            if (id == R.id.nav_home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (id == R.id.nav_favorites) {
                loadFragment(new FavoritesFragment());
                return true;
            } else if (id == R.id.nav_add) {
                loadFragment(new AddBookFragment());
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    public void navigateToDetail(int bookId) {
        detailFragmentContainer.setVisibility(View.VISIBLE);
        if (verticalDivider != null) verticalDivider.setVisibility(View.VISIBLE);
        if (heroMascot != null) heroMascot.setVisibility(View.GONE);
        if (heroSubtitle != null) heroSubtitle.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailFragmentContainer, DetailFragment.newInstance(bookId))
                .commit();
    }

    public void closeDetailPanel() {
        detailFragmentContainer.setVisibility(View.GONE);
        if (verticalDivider != null) verticalDivider.setVisibility(View.GONE);
        if (heroMascot != null) heroMascot.setVisibility(View.VISIBLE);
        if (heroSubtitle != null) heroSubtitle.setVisibility(View.VISIBLE);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.detailFragmentContainer);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (detailFragmentContainer.getVisibility() == View.VISIBLE) {
            closeDetailPanel();
        } else {
            super.onBackPressed();
        }
    }
}