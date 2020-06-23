package com.example.workouttracker;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WorkoutViewModel extends AndroidViewModel {
    private WorkoutRepository workoutRepository;

    private LiveData<List<tbl_workout>> allWorkouts;

    public WorkoutViewModel(Application application){
        super(application);
        workoutRepository = new WorkoutRepository(application);
        allWorkouts = workoutRepository.getAllWorkouts();
    }

    LiveData<List<tbl_workout>> getAllWorkouts() {return allWorkouts;}

    public void insertWorkout(tbl_workout txt_name, tbl_workout date_start){workoutRepository.insertWorkout(txt_name, date_start);}
}
