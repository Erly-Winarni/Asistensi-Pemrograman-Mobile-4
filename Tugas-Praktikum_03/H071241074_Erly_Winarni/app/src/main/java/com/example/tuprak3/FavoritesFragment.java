package com.example.tuprak3;

import android.os.Bundle;
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

import java.util.List;

public class FavoritesFragment extends Fragment {

    private BookAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvEmpty;

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

        List<Book> favs = DataManager.getInstance().getFavoriteBooks();

        adapter = new BookAdapter(requireContext(), favs, book -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToDetail(book.getId());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        updateEmptyState(favs);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList() {
        List<Book> favs = DataManager.getInstance().getFavoriteBooks();
        if (adapter != null) {
            adapter.updateList(favs);
        }
        updateEmptyState(favs);
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