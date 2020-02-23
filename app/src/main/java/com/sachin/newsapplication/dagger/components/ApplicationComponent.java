package com.sachin.newsapplication.dagger.components;


import com.sachin.newsapplication.MainActivity;
import com.sachin.newsapplication.dagger.modules.AppModule;
import com.sachin.newsapplication.dagger.modules.NetworkModule;
import com.sachin.newsapplication.dagger.modules.SharedPreferenceModule;
import com.sachin.newsapplication.dagger.modules.ViewModelModule;
import com.sachin.newsapplication.ui.main.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 *  Dagger single app component used for injecting SharedPreferenceModule.class, NetworkModule.class, RealmDatabaseModule.class
 */
@Singleton
@Component(modules = {SharedPreferenceModule.class, NetworkModule.class, ViewModelModule.class, AppModule.class})
public interface ApplicationComponent {

    void inject(MainFragment mainFragment);

    void inject(MainActivity activity);
}