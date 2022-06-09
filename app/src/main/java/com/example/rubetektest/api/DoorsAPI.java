package com.example.rubetektest.api;

import com.example.rubetektest.api.POJOs.DoorsPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DoorsAPI {
    @GET("http://cars.cprogroup.ru/api/rubetek/doors/")
    Call<DoorsPOJO> getDoors();
}
