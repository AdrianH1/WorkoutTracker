package com.example.workouttracker;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class NewWorkoutFragment extends Fragment {

    private WorkoutViewModel mWorkoutViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_new_workout, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_existing_workout);

        final WorkoutListAdapter adapter = new WorkoutListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mWorkoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        mWorkoutViewModel.getAllWorkouts().observe(getViewLifecycleOwner(), new Observer<List<tbl_workout>>() {
            @Override
            public void onChanged(@Nullable final List<tbl_workout> mWorkouts) {
                //Update the cached copy of the words in the adapter
                adapter.setWorkouts(mWorkouts);
            }
        });
        return rootView;
    }
}