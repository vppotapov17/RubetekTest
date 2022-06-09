package com.example.rubetektest.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final String baseURL = "http://cars.cprogroup.ru/api/rubetek/";

    private static NetworkService mInstance;

    private Retrofit mRetrofit;

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build();
    }

    public static NetworkService getInstance(){
        if (mInstance == null) mInstance = new NetworkService();
        return mInstance;
    }

    public CamerasAPI getCamerasAPI(){
        return mRetrofit.create(CamerasAPI.class);
    }

    public DoorsAPI getDoorsAPI(){
        return mRetrofit.create(DoorsAPI.class);
    }
}
