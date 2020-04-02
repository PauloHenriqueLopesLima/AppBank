package com.neomatrix.appbank.service;

import com.neomatrix.appbank.service.api.StatementsApi;
import com.neomatrix.appbank.service.api.UserApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    private static RetrofitService instance;
    private static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    private Retrofit getRetrofit(){
        if (retrofit == null){
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
            clientBuilder.readTimeout(30, TimeUnit.SECONDS);
            clientBuilder.writeTimeout(30, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(clientBuilder.build())
                    .build();

        }
        return retrofit;
    }

    public static synchronized RetrofitService getInstance(){
        if (instance==null){
            instance = new RetrofitService();
        }return instance;
    }

    public UserApi getUserApi(){
        return getRetrofit().create(UserApi.class);
    }

    public StatementsApi getStatementsApi(){
        return getRetrofit().create(StatementsApi.class);
    }
}