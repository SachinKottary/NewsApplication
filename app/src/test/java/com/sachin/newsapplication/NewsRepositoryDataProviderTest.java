package com.sachin.newsapplication;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.sachin.newsapplication.network.NetworkManager;
import com.sachin.newsapplication.network.NewsRepositoryDataProvider;
import com.sachin.newsapplication.network.dto.NewsItemDetailResponse;
import com.sachin.newsapplication.utils.ComparatorProvider;
import com.sachin.newsapplication.utils.FilterOption;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewsRepositoryDataProviderTest {

    @Mock
    NetworkManager networkManagerMock;
    @Mock
    ComparatorProvider comparatorProviderMock;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    NewsApplication application;
    @Mock
    MutableLiveData<List<NewsItemDetailResponse>> newsLiveDataMock;
    MutableLiveData<List<NewsItemDetailResponse>> newsLiveData = new MutableLiveData<>();
    private ComparatorProvider comparatorProvider = new ComparatorProvider();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private NewsRepositoryDataProvider dataProvider;

    private Observable<List<NewsItemDetailResponse>> newsObservable = Observable.create(e -> {
                e.onNext(new ArrayList<>());
                e.onComplete();
            }
    );

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        application.onCreate();
        dataProvider = new NewsRepositoryDataProvider(networkManagerMock, comparatorProviderMock);
        when(networkManagerMock.getNewsDetailList()).thenReturn(newsObservable);
    }

    @Test
    public void checkIfNewsApiCalled() {
        dataProvider.downloadNewsDetailsFromServer();
        verify(networkManagerMock, atLeastOnce()).getNewsDetailList();
    }
    @Test
    public void checkNoDataPresentWhenFilterOptionIsNull() {
        Whitebox.setInternalState(dataProvider, "newsListLiveData", newsLiveDataMock);
        when(newsLiveDataMock.getValue()).thenReturn(new ArrayList<>());
        dataProvider.filterNewsDetails(null);
        verify(newsLiveDataMock, never()).setValue(Mockito.any());
    }

    @Test
    public void checkIfDataSortedIfFilteredByPopularity() {
        List<NewsItemDetailResponse> responses = new ArrayList<>();
        responses.add(new NewsItemDetailResponse(123L, 2));
        responses.add(new NewsItemDetailResponse(200L, 1));
        Whitebox.setInternalState(dataProvider, "newsListLiveData", newsLiveData);
        newsLiveData.setValue(responses);
        when(comparatorProviderMock.getComparator(FilterOption.POPULAR)).thenReturn(comparatorProvider.getComparator(FilterOption.POPULAR));
        dataProvider.filterNewsDetails(FilterOption.POPULAR);
        Assert.assertEquals(1, responses.get(0).getRank());
    }

    @Test
    public void checkIfDataSortedIfFilteredByRecent() {
        List<NewsItemDetailResponse> responses = new ArrayList<>();
        responses.add(new NewsItemDetailResponse(1213L, 2));
        responses.add(new NewsItemDetailResponse(200L, 1));
        Whitebox.setInternalState(dataProvider, "newsListLiveData", newsLiveData);
        newsLiveData.setValue(responses);
        when(comparatorProviderMock.getComparator(FilterOption.RECENT)).thenReturn(comparatorProvider.getComparator(FilterOption.RECENT));
        dataProvider.filterNewsDetails(FilterOption.RECENT);
        Assert.assertTrue(responses.get(0).getTimeCreated() > responses.get(1).getTimeCreated());
    }

    @Test
    public void checkIfEverythingIsDisposed() {
        Whitebox.setInternalState(dataProvider, "compositeDisposable", compositeDisposable);
        compositeDisposable.add(new Disposable() {
            @Override
            public void dispose() {}

            @Override
            public boolean isDisposed() {
                return false;
            }
        });
        dataProvider.clear();
        Assert.assertTrue(compositeDisposable.isDisposed());
    }

}
