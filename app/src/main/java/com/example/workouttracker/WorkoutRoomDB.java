package com.example.workouttracker;

import android.content.Context;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {tbl_workout.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class  WorkoutRoomDB extends RoomDatabase{

    public abstract tbl_workoutDAO tbl_workoutDAO();

    private static volatile WorkoutRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WorkoutRoomDB getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (WorkoutRoomDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WorkoutRoomDB.class, "workout_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static WorkoutRoomDB.Callback sRoomDatabaseCallback = new WorkoutRoomDB.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            //If you want to keep data through app restarts,
            //comment out the following block
            databaseWriteExecutor.execute(()-> {
                //Populate the database in the background.
                //If you want to start with more words, just add them.
                tbl_workoutDAO dao = INSTANCE.tbl_workoutDAO();
                dao.deleteAll();

                tbl_workout workout = new tbl_workout("asdf", System.currentTimeMillis());
                dao.insertWorkout(workout);

                tbl_workout workout2 = new tbl_workout("ghjk", System.currentTimeMillis());
                dao.insertWorkout(workout2);
            });
        }
    };
}
