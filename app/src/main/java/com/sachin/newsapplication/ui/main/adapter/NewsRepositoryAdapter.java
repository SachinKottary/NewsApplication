package com.sachin.newsapplication.ui.main.adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sachin.newsapplication.R;
import com.sachin.newsapplication.databinding.NewsListItemBinding;
import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;
import com.sachin.newsapplication.ui.main.viewholders.NewsRepositoryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryAdapter extends RecyclerView.Adapter<NewsRepositoryViewHolder> {
    private List<NewsItemDetailResponse> newsItemDetailsList = new ArrayList<>();

    public NewsRepositoryAdapter() {
    }

    @NonNull
    @Override
    public NewsRepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.news_list_item,
                        parent, false);
        return new NewsRepositoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRepositoryViewHolder holder, int position) {
        NewsItemDetailResponse detailResponse = newsItemDetailsList.get(position);
        holder.onBind(detailResponse);
    }

    @Override
    public int getItemCount() {
        return newsItemDetailsList.size();
    }

    public void setNewsItemDetailList(List<NewsItemDetailResponse> newItemDetailList) {
        if (newItemDetailList == null) return;
        newsItemDetailsList.clear();
        newsItemDetailsList.addAll(newItemDetailList);
        notifyDataSetChanged();
    }

}