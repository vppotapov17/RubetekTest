package com.example.rubetektest.repositories.retrofit;

import com.example.rubetektest.repositories.retrofit.POJOs.DoorsPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DoorsAPI {
    @GET("http://cars.cprogroup.ru/api/rubetek/doors/")
    Call<DoorsPOJO> getDoors();
}
