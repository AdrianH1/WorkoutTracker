package com.example.workouttracker;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface tbl_workoutDAO{

    //allowing the insert of the same word multiple times by passing a conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWorkout(tbl_workout workout_values);
//    void insertWorkout(tbl_workout txt_name, tbl_workout date_start);

    @Query("DELETE FROM tbl_workout")
    void deleteAll();

    @Query("SELECT * from tbl_workout ORDER BY date_start ASC")
    LiveData<List<tbl_workout>> getWorkoutsByDate();

}



