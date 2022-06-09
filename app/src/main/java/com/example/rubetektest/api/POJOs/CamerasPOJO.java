package com.example.rubetektest.api.POJOs;

import com.google.gson.annotations.SerializedName;

public class CamerasPOJO {

    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private Data data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
