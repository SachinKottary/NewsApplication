package com.sachin.newsapplication.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferenceModule {

    private Context context;
    private static final String PREFERENCE_NAME = "NewsApp";

    public SharedPreferenceModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    AppPreferenceManager provideAppPreferencemanager(SharedPreferences sharedPreferences) {
        return new AppPreferenceManager(sharedPreferences);
    }

}
