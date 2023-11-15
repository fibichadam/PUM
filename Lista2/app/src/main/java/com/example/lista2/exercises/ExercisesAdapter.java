package com.example.lista2.exercises;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.classes.Exercise;
import com.example.lista2.databinding.ExerciseItemBinding;

import java.util.ArrayList;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesViewHolder> {
    private final ArrayList<Exercise> exerciseList;

    public ExercisesAdapter(ArrayList<Exercise> exerciseList)
    {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExercisesViewHolder(ExerciseItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        holder.bind(exerciseList.get(position));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
