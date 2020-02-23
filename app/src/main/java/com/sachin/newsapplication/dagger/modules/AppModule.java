package com.sachin.newsapplication.dagger.modules;

import com.sachin.newsapplication.network.NetworkManager;
import com.sachin.newsapplication.network.NewsRepositoryDataProvider;
import com.sachin.newsapplication.utils.ComparatorProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public ComparatorProvider getComparator() {
        return new ComparatorProvider();
    }

    @Provides
    @Singleton
    public NewsRepositoryDataProvider getDataProvider(NetworkManager networkManager, ComparatorProvider provider) {
        return new NewsRepositoryDataProvider(networkManager, provider);
    }

}