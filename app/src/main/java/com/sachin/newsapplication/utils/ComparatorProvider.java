package com.sachin.newsapplication.utils;

import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;

import java.util.Comparator;

public class ComparatorProvider {

    private Comparator<NewsItemDetailResponse> recentComparator = (o1, o2) -> ((int) (o2.getTimeCreated() - o1.getTimeCreated()));
    private Comparator<NewsItemDetailResponse> popularComparator = (o1, o2) -> ((int) (o1.getRank() - o2.getRank()));

    public Comparator<NewsItemDetailResponse> getComparator(FilterOption option) {
        return (option == FilterOption.POPULAR) ? popularComparator : recentComparator;
    }

}
