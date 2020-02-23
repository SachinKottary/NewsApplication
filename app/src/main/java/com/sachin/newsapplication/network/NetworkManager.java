package com.sachin.newsapplication.network;

import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;
import com.sachin.newsapplication.utils.NetworkUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * Helper class to handle network related operations
 */

public class NetworkManager {

    private RetrofitApiInterface retrofitApiInterface;

    public NetworkManager(RetrofitApiInterface RetrofitInterface) {
        retrofitApiInterface = RetrofitInterface;
    }

    public Observable<List<NewsItemDetailResponse>> getNewsDetailList() {
        return retrofitApiInterface.getNewsDetailsList(NetworkUtils.getUrl(ServerConstants.REQUEST_GET_NES_LIST));
    }

}