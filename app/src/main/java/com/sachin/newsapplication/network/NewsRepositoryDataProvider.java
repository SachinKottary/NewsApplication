package com.sachin.newsapplication.network;

import androidx.lifecycle.MutableLiveData;
import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;
import com.sachin.newsapplication.utils.ComparatorProvider;
import com.sachin.newsapplication.utils.Constant;
import com.sachin.newsapplication.utils.FilterOption;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepositoryDataProvider {

    public MutableLiveData<List<NewsItemDetailResponse>> newsListLiveData = new MutableLiveData<>();
    public MutableLiveData<String> newsErrorLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private NetworkManager networkManager;
    private ComparatorProvider comparatorProvider;

    public NewsRepositoryDataProvider(NetworkManager manager, ComparatorProvider provider) {
        networkManager = manager;
        comparatorProvider = provider;
    }

    public void clear() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) return;
        compositeDisposable.dispose();
    }

    public void downloadNewsDetailsFromServer() {
        networkManager.getNewsDetailList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiCallbackObserver);
    }

    public void filterNewsDetails(FilterOption option) {
        if (option == null || newsListLiveData.getValue() == null) return;
        List<NewsItemDetailResponse> detailResponseList = newsListLiveData.getValue();
        Collections.sort(detailResponseList, comparatorProvider.getComparator(option));
        newsListLiveData.setValue(detailResponseList);
    }

    private Observer<List<NewsItemDetailResponse>> apiCallbackObserver = new Observer<List<NewsItemDetailResponse>>() {
        @Override
        public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
        }

        @Override
        public void onNext(List<NewsItemDetailResponse> detailsList) {
            if (detailsList == null || detailsList.isEmpty()) newsErrorLiveData.setValue(Constant.ERROR_NO_DATA);
            newsListLiveData.setValue(detailsList);
        }

        @Override
        public void onError(Throwable e) {
            newsErrorLiveData.setValue(e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    };


}