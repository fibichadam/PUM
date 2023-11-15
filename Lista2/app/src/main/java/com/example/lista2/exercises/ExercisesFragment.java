package com.example.lista2.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista2.MainActivity;
import com.example.lista2.classes.Exercise;
import com.example.lista2.classes.ExerciseList;
import com.example.lista2.databinding.FragmentExercisesBinding;
import com.example.lista2.exerciseLists.ExerciseListsAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ExercisesFragment extends Fragment {

    private FragmentExercisesBinding binding;
    public ExercisesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExercisesBinding.inflate(inflater);

        Bundle bundle = getArguments();
        ExerciseList list = MainActivity.exerciseLists.get(bundle.getInt("listNumber"));

        binding.recyclerView.setAdapter(new ExercisesAdapter(list.exercises));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.listName.setText(list.subject.name+"\nLista "+list.listNumber);

        return binding.getRoot();
    }
}