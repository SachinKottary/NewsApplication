package com.sachin.newsapplication;

import com.sachin.newsapplication.network.NewsRepositoryDataProvider;
import com.sachin.newsapplication.ui.main.NewsViewModel;
import com.sachin.newsapplication.utils.FilterOption;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class NewsViewModelTest {
    @Mock
    NewsRepositoryDataProvider dataProviderMock;
    private NewsViewModel newsViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        newsViewModel = new NewsViewModel(dataProviderMock);
    }

    @Test
    public void checkIfNewsDownloadApiCalled() {
        newsViewModel.downloadNewsDataFromServer();
        verify(dataProviderMock, atLeastOnce()).downloadNewsDetailsFromServer();
    }

    @Test
    public void checkIfFilterMethodIsCalledWithRecentType() {
        newsViewModel.filterNewList(FilterOption.RECENT);
        verify(dataProviderMock, atLeastOnce()).filterNewsDetails(FilterOption.RECENT);
    }

    @Test
    public void checkIfFilterMethodIsCalledWithPopularType() {
        newsViewModel.filterNewList(FilterOption.POPULAR);
        verify(dataProviderMock, atLeastOnce()).filterNewsDetails(FilterOption.POPULAR);
    }

}