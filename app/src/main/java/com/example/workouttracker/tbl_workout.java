package com.example.workouttracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_workout")
public class tbl_workout {

    @PrimaryKey(autoGenerate = true)
    public int pk_id;

    @NonNull
    @ColumnInfo(name = "txt_name")
    private String txt_name;

    @NonNull
    @ColumnInfo(name = "date_start")
    private long date_start;

    public tbl_workout(String txt_name, long date_start){
                this.txt_name = txt_name;
        this.date_start = date_start;
    }

    public int getPk_id(){return this.pk_id;}
    public String getTxt_name(){return this.txt_name;}
    public long getDate_start(){return this.date_start;}

}
