package com.example.tuprak1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostGridAdapter extends RecyclerView.Adapter<PostGridAdapter.PostGridViewHolder> {

    private Context context;
    private List<Integer> imageList;

    public PostGridAdapter(Context context, List<Integer> imageList) {
        this.context   = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public PostGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_grid, parent, false);
        return new PostGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostGridViewHolder holder, int position) {
        int imageRes = imageList.get(position);
        holder.ivGridPost.setImageResource(imageRes);

        holder.ivGridPost.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetailActivity.class);
            intent.putExtra("post_image", imageRes);
            intent.putExtra("caption", "");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class PostGridViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGridPost;

        public PostGridViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGridPost = itemView.findViewById(R.id.ivGridPost);
        }
    }
}