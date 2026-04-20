package com.example.tuprak3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tuprak3.R;
import com.example.tuprak3.Book;
import com.example.tuprak3.DataManager;

public class AddBookFragment extends Fragment {

    private ImageView imgCoverPreview;
    private EditText  etTitle, etAuthor, etYear, etGenre, etBlurb, etReview;
    private RatingBar ratingBar;
    private Uri       selectedImageUri = null;

    private final ActivityResultLauncher<Intent> galleryLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    imgCoverPreview.setImageURI(selectedImageUri);
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imgCoverPreview = view.findViewById(R.id.imgCoverPreview);
        etTitle         = view.findViewById(R.id.etTitle);
        etAuthor        = view.findViewById(R.id.etAuthor);
        etYear          = view.findViewById(R.id.etYear);
        etGenre         = view.findViewById(R.id.etGenre);
        etBlurb         = view.findViewById(R.id.etBlurb);
        etReview        = view.findViewById(R.id.etReview);
        ratingBar       = view.findViewById(R.id.ratingBar);
        Button btnPickImage = view.findViewById(R.id.btnPickImage);
        Button btnSave      = view.findViewById(R.id.btnSave);

        btnPickImage.setOnClickListener(v -> openGallery());
        btnSave.setOnClickListener(v -> saveBook());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }

    private void saveBook() {
        String title  = etTitle.getText().toString().trim();
        String author = etAuthor.getText().toString().trim();
        String yearStr = etYear.getText().toString().trim();
        String genre  = etGenre.getText().toString().trim();
        String blurb  = etBlurb.getText().toString().trim();
        String review = etReview.getText().toString().trim();
        float  rating = ratingBar.getRating();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()
                || genre.isEmpty() || blurb.isEmpty()) {
            Toast.makeText(requireContext(),
                    "Harap lengkapi semua field yang wajib diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Tahun tidak valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        Book newBook;
        if (selectedImageUri != null) {
            newBook = new Book(title, author, year, blurb, genre, rating, review, selectedImageUri);
        } else {
            newBook = new Book(title, author, year, blurb, genre, rating, review,
                    R.drawable.cover_placeholder);
        }

        DataManager.getInstance().addBook(newBook);

        Toast.makeText(requireContext(), "Buku \"" + title + "\" berhasil ditambahkan!",
                Toast.LENGTH_SHORT).show();

        clearForm();
    }

    private void clearForm() {
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etGenre.setText("");
        etBlurb.setText("");
        etReview.setText("");
        ratingBar.setRating(0);
        imgCoverPreview.setImageResource(R.drawable.ic_image_placeholder);
        selectedImageUri = null;
    }
}