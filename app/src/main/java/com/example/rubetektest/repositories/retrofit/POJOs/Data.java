package com.example.rubetektest.repositories.retrofit.POJOs;

import com.example.rubetektest.models.camera;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("room")
    private List<String> room;
    @SerializedName("cameras")
    private List<camera> cameras;

    public List<String> getRoom() {
        return room;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }

    public List<camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<camera> cameras) {
        this.cameras = cameras;
    }
}
