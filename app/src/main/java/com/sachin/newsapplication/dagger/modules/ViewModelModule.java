package com.sachin.newsapplication.dagger.modules;

import androidx.lifecycle.ViewModel;

import com.sachin.newsapplication.base.ViewModelFactory;
import com.sachin.newsapplication.network.NewsRepositoryDataProvider;
import com.sachin.newsapplication.ui.main.NewsViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    ViewModel viewModel1(NewsRepositoryDataProvider fetchDataUseCase1) {
        return new NewsViewModel(fetchDataUseCase1);
    }
}