package com.example.tuprak3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak3.R;
import com.example.tuprak3.BookAdapter;
import com.example.tuprak3.Book;
import com.example.tuprak3.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    private BookAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private View loadingLayout;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerFavorites);
        tvEmpty      = view.findViewById(R.id.tvEmpty);
        loadingLayout = view.findViewById(R.id.loadingLayout);

        adapter = new BookAdapter(requireContext(), new ArrayList<>(), book -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToDetail(book.getId());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        loadFavoriteBooks();
    }

    private void loadFavoriteBooks() {
        loadingLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);

        executorService.execute(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            List<Book> favs = DataManager.getInstance().getFavoriteBooks();
            handler.post(() -> {
                if (adapter != null) {
                    adapter.updateList(favs);
                }
                updateEmptyState(favs);
                loadingLayout.setVisibility(View.GONE);
            });
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList() {
        if (loadingLayout == null) return;
        
        loadingLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);

        executorService.execute(() -> {
            List<Book> favs = DataManager.getInstance().getFavoriteBooks();
            handler.post(() -> {
                if (adapter != null) {
                    adapter.updateList(favs);
                }
                updateEmptyState(favs);
                loadingLayout.setVisibility(View.GONE);
            });
        });
    }

    private void updateEmptyState(List<Book> list) {
        if (list.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}