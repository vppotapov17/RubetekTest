package com.example.rubetektest.repositories.retrofit.POJOs;

import com.example.rubetektest.models.door;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoorsPOJO {
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private List<door> doorList;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<door> getDoorList() {
        return doorList;
    }

    public void setDoorList(List<door> doorList) {
        this.doorList = doorList;
    }
}
