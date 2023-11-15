package com.example.lista2.exerciseLists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista2.MainActivity;
import com.example.lista2.databinding.FragmentExerciseListsBinding;

public class ExerciseListsFragment extends Fragment {

    private FragmentExerciseListsBinding binding;
    public ExerciseListsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseListsBinding.inflate(inflater);

        binding.recyclerView.setAdapter(new ExerciseListsAdapter(MainActivity.exerciseLists));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return binding.getRoot();
    }
}