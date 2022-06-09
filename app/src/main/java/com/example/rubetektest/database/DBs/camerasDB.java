package com.example.rubetektest.database.DBs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rubetektest.models.camera;
import com.example.rubetektest.database.DAOs.camerasDAO;

@Database(entities = {camera.class}, version = 1)
public abstract class camerasDB extends RoomDatabase {
    public abstract camerasDAO getCamerasDao();
}
