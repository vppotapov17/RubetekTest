package com.example.rubetektest.database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rubetektest.models.camera;

import java.util.List;

@Dao
public interface camerasDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCamera(camera CAMERA);
    @Delete
    void deleteCamera(camera CAMERA);
    @Update
    void updateCamera(camera CAMERA);
    @Query("select * from camera")
    List<camera> getCameras();
}
