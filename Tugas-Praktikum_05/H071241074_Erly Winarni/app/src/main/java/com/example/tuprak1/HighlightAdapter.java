package com.example.tuprak1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.HighlightViewHolder> {
    private Context context;
    private List<HighlightItem> highlightList;

    public HighlightAdapter(Context context, List<HighlightItem> highlightList) {
        this.context       = context;
        this.highlightList = highlightList;
    }

    @NonNull
    @Override
    public HighlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_highlight, parent, false);
        return new HighlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightViewHolder holder, int position) {
        HighlightItem item = highlightList.get(position);

        holder.ivHighlight.setImageResource(item.getCoverImage());
        holder.tvLabel.setText(item.getLabel());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoryViewerActivity.class);
            intent.putExtra("images", item.getImages());
            intent.putExtra("start_index", 0);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public static class HighlightViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView ivHighlight;
        TextView tvLabel;

        public HighlightViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHighlight = itemView.findViewById(R.id.ivHighlight);
            tvLabel     = itemView.findViewById(R.id.tvHighlightLabel);
        }
    }
}