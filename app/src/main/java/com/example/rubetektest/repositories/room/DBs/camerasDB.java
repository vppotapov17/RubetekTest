package com.example.rubetektest.repositories.room.DBs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rubetektest.models.camera;
import com.example.rubetektest.repositories.room.DAOs.camerasDAO;

@Database(entities = {camera.class}, version = 1)
public abstract class camerasDB extends RoomDatabase {
    public abstract camerasDAO getCamerasDao();
}
