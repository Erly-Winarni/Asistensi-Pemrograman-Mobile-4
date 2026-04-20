package com.example.tuprak3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tuprak3.R;
import com.example.tuprak3.Book;
import com.example.tuprak3.DataManager;

public class DetailFragment extends Fragment {

    private static final String ARG_BOOK_ID = "book_id";

    private Book currentBook;
    private Button btnLike;

    public static DetailFragment newInstance(int bookId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_BOOK_ID, bookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int bookId = -1;
        if (getArguments() != null) {
            bookId = getArguments().getInt(ARG_BOOK_ID, -1);
        }

        for (Book b : DataManager.getInstance().getAllBooks()) {
            if (b.getId() == bookId) {
                currentBook = b;
                break;
            }
        }

        if (currentBook == null) return;

        ImageView imgCover       = view.findViewById(R.id.detailImgCover);
        View heroGradient        = view.findViewById(R.id.detailHeroGradient);
        View textScrim           = view.findViewById(R.id.detailTextScrim);
        TextView tvTitle         = view.findViewById(R.id.detailTvTitle);
        TextView tvAuthor        = view.findViewById(R.id.detailTvAuthor);
        TextView tvYear          = view.findViewById(R.id.detailTvYear);
        TextView tvGenre         = view.findViewById(R.id.detailTvGenre);
        RatingBar ratingBar      = view.findViewById(R.id.detailRatingBar);
        TextView tvRating        = view.findViewById(R.id.detailTvRating);
        TextView tvBlurb         = view.findViewById(R.id.detailTvBlurb);
        TextView tvReview        = view.findViewById(R.id.detailTvReview);
        btnLike                  = view.findViewById(R.id.btnLike);

        boolean hasCover = false;
        if (currentBook.getCoverUri() != null) {
            imgCover.setImageURI(currentBook.getCoverUri());
            hasCover = true;
        } else if (currentBook.getCoverResId() != 0 && currentBook.getCoverResId() != R.drawable.cover_placeholder) {
            imgCover.setImageResource(currentBook.getCoverResId());
            hasCover = true;
        }

        if (hasCover) {
            imgCover.setVisibility(View.VISIBLE);
            heroGradient.setVisibility(View.GONE);
            // Tampilkan scrim gelap agar teks putih tetap terbaca di atas gambar
            if (textScrim != null) textScrim.setVisibility(View.VISIBLE);
        } else {
            imgCover.setVisibility(View.GONE);
            heroGradient.setVisibility(View.VISIBLE);
            if (textScrim != null) textScrim.setVisibility(View.GONE);
        }

        tvTitle.setText(currentBook.getTitle());
        tvAuthor.setText(currentBook.getAuthor());
        tvYear.setText(String.valueOf(currentBook.getYear()));
        tvGenre.setText(currentBook.getGenre());
        ratingBar.setRating(currentBook.getRating());
        tvRating.setText(String.format("%.1f / 5.0", currentBook.getRating()));
        tvBlurb.setText(currentBook.getBlurb());
        tvReview.setText(currentBook.getReview().isEmpty()
                ? "Belum ada review." : currentBook.getReview());

        updateLikeButton();

        btnLike.setOnClickListener(v -> toggleLike());

        view.findViewById(R.id.btnBack).setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).closeDetailPanel();
            }
        });
    }

    private void toggleLike() {
        currentBook.setLiked(!currentBook.isLiked());
        updateLikeButton();

        Fragment masterFragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (masterFragment instanceof HomeFragment) {
            ((HomeFragment) masterFragment).refreshList();
        } else if (masterFragment instanceof FavoritesFragment) {
            ((FavoritesFragment) masterFragment).refreshList();
        }

        String msg = currentBook.isLiked()
                ? "\"" + currentBook.getTitle() + "\" ditambahkan ke Favorit!"
                : "\"" + currentBook.getTitle() + "\" dihapus dari Favorit.";
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void updateLikeButton() {
        if (currentBook.isLiked()) {
            btnLike.setText("Hapus dari Favorit");
            btnLike.setBackgroundTintList(
                    requireContext().getColorStateList(R.color.liked_color));
        } else {
            btnLike.setText("Tambah ke Favorit");
            btnLike.setBackgroundTintList(
                    requireContext().getColorStateList(R.color.primary_color));
        }
    }
}
