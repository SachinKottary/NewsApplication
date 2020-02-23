package com.sachin.newsapplication;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.sachin.newsapplication.dagger.components.ApplicationComponent;
import com.sachin.newsapplication.dagger.components.DaggerApplicationComponent;
import com.sachin.newsapplication.dagger.modules.AppModule;
import com.sachin.newsapplication.dagger.modules.NetworkModule;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsApplication extends Application {

    private ApplicationComponent applicationComponent;
    private static NewsApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule())
                .build();
        context = this;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static NewsApplication getApplication() {
        return context;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        clearGlideCacheMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_LOW || level == TRIM_MEMORY_RUNNING_CRITICAL || level == TRIM_MEMORY_UI_HIDDEN) {
            clearGlideCacheMemory();
        }
    }

    /**
     *  Free up some memory in case of low memory
     */
    public void clearGlideCacheMemory() {
        Completable.fromAction(() ->
                Glide.get(getApplication()).clearDiskCache())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Glide.get(getApplication()).clearMemory();
                    Glide.get(this).setMemoryCategory(MemoryCategory.LOW);
                });
    }


}
