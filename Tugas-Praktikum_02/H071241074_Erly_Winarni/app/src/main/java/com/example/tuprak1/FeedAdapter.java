package com.example.tuprak1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private Context context;
    private List<FeedItem> feedList;

    public FeedAdapter(Context context, List<FeedItem> feedList) {
        this.context  = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedItem item = feedList.get(position);

        holder.ivAvatar.setImageResource(item.getAvatarRes());
        holder.tvUsername.setText(item.getUsername());
        holder.tvLocation.setText(item.getLocation());
        holder.ivFeedImage.setImageResource(item.getImageRes());
        holder.tvLikesCount.setText(item.getLikes() + " suka");
        holder.tvCaption.setText(item.getUsername() + "  " + item.getCaption());
        holder.tvTime.setText(item.getTime());

        View.OnClickListener goToProfile = v -> {
            HighlightItem[] highlights = item.getHighlights();

            int count = highlights.length;
            String[] labels = new String[count];
            int[] sizes = new int[count];

            int totalImages = 0;
            for (int i = 0; i < count; i++) {
                labels[i] = highlights[i].getLabel();
                sizes[i]  = highlights[i].getImages().length;
                totalImages += sizes[i];
            }

            int[] allImages = new int[totalImages];
            int idx = 0;
            for (HighlightItem h : highlights) {
                for (int img : h.getImages()) {
                    allImages[idx++] = img;
                }
            }

            Intent intent = new Intent(context, OtherProfileActivity.class);
            intent.putExtra("username",      item.getUsername());
            intent.putExtra("fullName",      item.getFullName());
            intent.putExtra("bio",           item.getBio());
            intent.putExtra("pronouns",      item.getPronouns());
            intent.putExtra("followers",     item.getFollowers());
            intent.putExtra("following",     item.getFollowing());
            intent.putExtra("postCount",     item.getPostCount());
            intent.putExtra("avatarRes",     item.getAvatarRes());
            intent.putExtra("postImages",    item.getPostImages());
            intent.putExtra("hlLabels",      labels);
            intent.putExtra("hlSizes",       sizes);
            intent.putExtra("hlAllImages",   allImages);
            context.startActivity(intent);
        };
        holder.ivAvatar.setOnClickListener(goToProfile);
        holder.tvUsername.setOnClickListener(goToProfile);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView ivAvatar;
        TextView tvUsername, tvLocation, tvLikesCount, tvCaption, tvTime;
        ImageView ivFeedImage, ivLike, ivSave;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar     = itemView.findViewById(R.id.ivFeedAvatar);
            tvUsername   = itemView.findViewById(R.id.tvFeedUsername);
            tvLocation   = itemView.findViewById(R.id.tvFeedLocation);
            ivFeedImage  = itemView.findViewById(R.id.ivFeedImage);
            tvLikesCount = itemView.findViewById(R.id.tvLikesCount);
            tvCaption    = itemView.findViewById(R.id.tvFeedCaption);
            tvTime       = itemView.findViewById(R.id.tvFeedTime);
            ivLike       = itemView.findViewById(R.id.ivLike);
            ivSave       = itemView.findViewById(R.id.ivSave);
        }
    }
}
