package com.sachin.newsapplication.ui.main.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sachin.newsapplication.databinding.NewsListItemBinding;
import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;

/**
 * Used as view holder for git repository details
 */
public class NewsRepositoryViewHolder extends RecyclerView.ViewHolder {

    public NewsListItemBinding newsListItemBinding;

    public NewsRepositoryViewHolder(@NonNull NewsListItemBinding itemView) {
        super(itemView.getRoot());
        newsListItemBinding = itemView;
    }

    public void onBind(NewsItemDetailResponse details) {
        newsListItemBinding.setNewsDetails(details);
    }

}