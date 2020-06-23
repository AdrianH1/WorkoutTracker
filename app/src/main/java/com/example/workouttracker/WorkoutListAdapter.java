package com.example.workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutViewHolder> {

    class WorkoutViewHolder extends RecyclerView.ViewHolder{
        private final TextView workoutItemView;

        private WorkoutViewHolder(View itemView) {
            super(itemView);
            workoutItemView = itemView.findViewById(R.id.workout_textView);
        }
    }

    private final LayoutInflater Inflater;
    private List<tbl_workout> mWorkouts; //Cached copy of workouts

    WorkoutListAdapter(Context context) {Inflater = LayoutInflater.from(context);}

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = Inflater.inflate(R.layout.recyclerview_workout_item, parent, false);
        return new WorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position){
        if (mWorkouts != null){
            tbl_workout current = mWorkouts.get(position);
            holder.workoutItemView.setText(current.getTxt_name());
        } else {
            //Covers the case of data not being ready yet.
            holder.workoutItemView.setText("No Workout");
        }
    }

    void setWorkouts(List<tbl_workout> mWorkouts){
        this.mWorkouts = mWorkouts;
        notifyDataSetChanged();
    }

    //getItemCount() is called many times, and when it is first called,
    // mWorkouts has not been updated (means initially, it's null, and we can't return null)
    @Override
    public int getItemCount(){
        if (mWorkouts != null)
            return mWorkouts.size();
        else return 0;
    }
}
