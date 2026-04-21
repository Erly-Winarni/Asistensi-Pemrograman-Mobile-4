package com.example.tuprak3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak3.R;
import com.example.tuprak3.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    public interface OnBookClickListener {
        void onBookClick(Book book);
    }
    private final Context context;
    private List<Book> bookList;
    private final OnBookClickListener listener;

    public BookAdapter(Context context, List<Book> bookList, OnBookClickListener listener) {
        this.context  = context;
        this.bookList = bookList;
        this.listener = listener;
    }

    public void updateList(List<Book> newList) {
        this.bookList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvYear.setText(String.valueOf(book.getYear()));
        holder.tvGenre.setText(book.getGenre());
        holder.tvRating.setText(String.valueOf(book.getRating()));

        if (book.getCoverUri() != null) {
            holder.imgCover.setImageURI(book.getCoverUri());
        } else {
            holder.imgCover.setImageResource(book.getCoverResId());
        }

        holder.imgLike.setVisibility(book.isLiked() ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(v -> listener.onBookClick(book));
    }

    @Override
    public int getItemCount() { return bookList.size(); }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover, imgLike;
        TextView  tvTitle, tvAuthor, tvYear, tvGenre, tvRating;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover  = itemView.findViewById(R.id.imgCover);
            imgLike   = itemView.findViewById(R.id.imgLike);
            tvTitle   = itemView.findViewById(R.id.tvTitle);
            tvAuthor  = itemView.findViewById(R.id.tvAuthor);
            tvYear    = itemView.findViewById(R.id.tvYear);
            tvGenre   = itemView.findViewById(R.id.tvGenre);
            tvRating  = itemView.findViewById(R.id.tvRating);
        }
    }
}