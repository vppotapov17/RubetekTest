package com.example.rubetektest.repositories.room.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rubetektest.models.door;

import java.util.List;

@Dao
public interface doorsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDoor(door DOOR);
    @Delete()
    void deleteDoor(door DOOR);
    @Update
    void updateDoor(door DOOR);
    @Query("select * from door")
    List<door> getDoors();
}
