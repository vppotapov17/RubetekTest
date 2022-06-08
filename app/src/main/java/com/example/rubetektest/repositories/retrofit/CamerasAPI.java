package com.example.rubetektest.repositories.retrofit;

import com.example.rubetektest.repositories.retrofit.POJOs.CamerasPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CamerasAPI {
    @GET("http://cars.cprogroup.ru/api/rubetek/cameras/")
    Call<CamerasPOJO> getCameras();
}
