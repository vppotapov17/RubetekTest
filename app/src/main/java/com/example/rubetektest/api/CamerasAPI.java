package com.example.rubetektest.api;

import com.example.rubetektest.api.POJOs.CamerasPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CamerasAPI {
    @GET("http://cars.cprogroup.ru/api/rubetek/cameras/")
    Call<CamerasPOJO> getCameras();
}
