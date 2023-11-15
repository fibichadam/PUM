package com.example.lista2.exercises;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.classes.Exercise;
import com.example.lista2.databinding.ExerciseItemBinding;

public class ExercisesViewHolder extends RecyclerView.ViewHolder {
    private final ExerciseItemBinding binding;
    int exerciseNumber = 1;

    public ExercisesViewHolder(ExerciseItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(Exercise exercise){
        binding.exerciseNumber.setText("Zadanie "+String.valueOf(exercise.exerciseNumber));
        binding.points.setText("pkt "+String.valueOf(exercise.points));
        binding.exerciseDescription.setText(exercise.content);
    }
}
