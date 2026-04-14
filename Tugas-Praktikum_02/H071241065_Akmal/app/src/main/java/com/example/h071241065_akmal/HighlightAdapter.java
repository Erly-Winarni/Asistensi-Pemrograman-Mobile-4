package com.example.h071241065_akmal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071241065_akmal.model.Highlight;

import java.util.ArrayList;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.HighlightViewHolder> {

    private ArrayList<Highlight> listHighlight;
    private OnItemClickCallback onItemClickCallback;

    public interface OnItemClickCallback {
        void onItemClicked(Highlight data);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public HighlightAdapter(ArrayList<Highlight> list) {
        this.listHighlight = list;
    }

    @NonNull
    @Override
    public HighlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_highlight, parent, false);
        return new HighlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightViewHolder holder, int position) {
        Highlight highlight = listHighlight.get(position);

        holder.ivCover.setImageResource(highlight.getCoverImageResId());
        holder.tvTitle.setText(highlight.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickCallback != null) {
                onItemClickCallback.onItemClicked(highlight);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHighlight.size();
    }

    static class HighlightViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle;

        HighlightViewHolder(View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_highlight_cover);
            tvTitle = itemView.findViewById(R.id.tv_highlight_title);
        }
    }
}