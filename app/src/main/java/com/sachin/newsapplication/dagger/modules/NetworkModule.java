package com.sachin.newsapplication.dagger.modules;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sachin.newsapplication.BuildConfig;
import com.sachin.newsapplication.network.NetworkManager;
import com.sachin.newsapplication.network.RetrofitApiInterface;
import com.sachin.newsapplication.network.ServerConstants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger network module, used to initialize network component and
 * provide dependency to network manager
 */
@Module
public class NetworkModule {

    public NetworkModule() {
    }

    @Provides
    @NonNull
    OkHttpClient getHttpClientBuilder() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.readTimeout(60, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        HttpLoggingInterceptor headerInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(loggingInterceptor);
            clientBuilder.addInterceptor(headerInterceptor);
        }

        return clientBuilder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit ProvideRetrofit(Gson gson, final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ServerConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public RetrofitApiInterface providesNetworkApiInterface(
            Retrofit retrofit) {
        return retrofit.create(RetrofitApiInterface.class);
    }

    @Provides
    @Singleton
    public NetworkManager providesNetworkWrapper(
            RetrofitApiInterface networkApiInterface) {
        return new NetworkManager(networkApiInterface);
    }

}