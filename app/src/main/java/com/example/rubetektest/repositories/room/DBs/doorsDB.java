package com.example.rubetektest.repositories.room.DBs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rubetektest.models.door;
import com.example.rubetektest.repositories.room.DAOs.doorsDAO;

@Database(entities = door.class, version = 1)
public abstract class doorsDB extends RoomDatabase {
    public abstract doorsDAO getDoorsDao();
}
