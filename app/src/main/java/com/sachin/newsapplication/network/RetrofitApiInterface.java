package com.sachin.newsapplication.network;

import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RetrofitApiInterface {

    @GET
    Observable<List<NewsItemDetailResponse>> getNewsDetailsList(@Url String url);
}