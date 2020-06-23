package com.example.workouttracker;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WorkoutRepository {

    private tbl_workoutDAO workoutDAO;
    private LiveData<List<tbl_workout>> allWorkouts;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WorkoutRepository(Application application){
        WorkoutRoomDB db = WorkoutRoomDB.getDatabase(application);
        workoutDAO = db.tbl_workoutDAO();
        allWorkouts = workoutDAO.getWorkoutsByDate();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<tbl_workout>> getAllWorkouts(){
        return allWorkouts;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insertWorkout(tbl_workout txt_name, tbl_workout date_start){
        WorkoutRoomDB.databaseWriteExecutor.execute(() ->{
            workoutDAO.insertWorkout(txt_name);
//            workoutDAO.insertWorkout(txt_name, date_start);
        });
    }
}
