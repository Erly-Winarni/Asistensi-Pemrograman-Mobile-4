package com.example.h071241065_akmal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071241065_akmal.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Post> listPost;
    private OnItemClickCallback onItemClickCallback;

    // Interface untuk mendeteksi klik
    public interface OnItemClickCallback {
        void onProfileClicked(Post data);
        void onPostClicked(Post data);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public PostAdapter(ArrayList<Post> list) {
        this.listPost = list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menghubungkan adapter dengan item_post.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = listPost.get(position);

        // Memasang data ke tampilan
        holder.ivProfile.setImageResource(post.getProfileImageResId());
        holder.tvUsername.setText(post.getUsername());
        // Jika ada imageUri (hasil upload), tampilkan dari galeri
        if (post.getImageUri() != null) {
            holder.ivPostImage.setImageURI(android.net.Uri.parse(post.getImageUri()));
        } else {
            // Jika tidak, tampilkan gambar dummy bawaan
            holder.ivPostImage.setImageResource(post.getPostImageResId());
        }
        holder.tvCaption.setText(post.getCaption());

        // Aksi ketika Header (Profil/Username) diklik
        holder.layoutHeader.setOnClickListener(v -> {
            if (onItemClickCallback != null) {
                onItemClickCallback.onProfileClicked(post);
            }
        });

        // Aksi ketika Gambar Postingan diklik
        holder.ivPostImage.setOnClickListener(v -> {
            if (onItemClickCallback != null) {
                onItemClickCallback.onPostClicked(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivPostImage;
        TextView tvUsername, tvCaption;
        LinearLayout layoutHeader;

        PostViewHolder(View itemView) {
            super(itemView);
            // Inisialisasi ID dari item_post.xml
            ivProfile = itemView.findViewById(R.id.iv_profile_post);
            tvUsername = itemView.findViewById(R.id.tv_username_post);
            ivPostImage = itemView.findViewById(R.id.iv_image_post);
            tvCaption = itemView.findViewById(R.id.tv_caption_post);
            layoutHeader = itemView.findViewById(R.id.layout_header_post);
        }
    }
}