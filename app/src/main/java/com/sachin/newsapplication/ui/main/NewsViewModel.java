package com.sachin.newsapplication.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sachin.newsapplication.network.NewsRepositoryDataProvider;
import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;
import com.sachin.newsapplication.utils.FilterOption;

import java.util.List;

import javax.inject.Inject;

public class NewsViewModel extends ViewModel {

    private NewsRepositoryDataProvider newsRepositoryDataProvider;
    public MutableLiveData<List<NewsItemDetailResponse>> newsListLiveData;
    public MutableLiveData<String> errorLiveData;

    public NewsViewModel() {}

    @Inject
    public NewsViewModel(NewsRepositoryDataProvider dataProvider) {
        newsRepositoryDataProvider = dataProvider;
        newsListLiveData = dataProvider.newsListLiveData;
        errorLiveData = dataProvider.newsErrorLiveData;
        downloadNewsDataFromServer();
    }

    public void downloadNewsDataFromServer() {
        newsRepositoryDataProvider.downloadNewsDetailsFromServer();
    }

    public void filterNewList(FilterOption option) {
        newsRepositoryDataProvider.filterNewsDetails(option);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (newsRepositoryDataProvider != null) newsRepositoryDataProvider.clear();
    }

}
