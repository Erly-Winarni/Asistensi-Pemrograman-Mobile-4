package com.example.tuprak3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class HomeFragment extends Fragment {

    private BookAdapter adapter;
    private List<Book> displayList = new ArrayList<>();
    private View loadingLayout;
    private RecyclerView recyclerView;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        EditText     etSearch     = view.findViewById(R.id.etSearch);
        loadingLayout = view.findViewById(R.id.loadingLayout);

        adapter = new BookAdapter(requireContext(), new ArrayList<>(), book -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToDetail(book.getId());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBooks(s.toString());
            }
        });

        loadInitialData();
    }

    private void loadInitialData() {
        loadingLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        executorService.execute(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            
            List<Book> allBooks = DataManager.getInstance().getAllBooks();
            handler.post(() -> {
                displayList.clear();
                displayList.addAll(allBooks);
                adapter.updateList(displayList);
                loadingLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
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
        executorService.execute(() -> {
            List<Book> allBooks = DataManager.getInstance().getAllBooks();
            handler.post(() -> {
                displayList.clear();
                displayList.addAll(allBooks);
                if (adapter != null) {
                    adapter.updateList(displayList);
                }
                loadingLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        });
    }

    private void filterBooks(String query) {
        loadingLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        executorService.execute(() -> {
            try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            
            List<Book> filtered = new ArrayList<>();
            for (Book book : DataManager.getInstance().getAllBooks()) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filtered.add(book);
                }
            }
            handler.post(() -> {
                adapter.updateList(filtered);
                loadingLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        });
    }
}