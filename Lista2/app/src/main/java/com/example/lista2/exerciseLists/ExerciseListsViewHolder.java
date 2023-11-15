package com.example.lista2.exerciseLists;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.classes.ExerciseList;
import com.example.lista2.databinding.ExerciseListsItemBinding;

public class ExerciseListsViewHolder extends RecyclerView.ViewHolder {
    private final ExerciseListsItemBinding binding;

    public ExerciseListsViewHolder(ExerciseListsItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(ExerciseList list){
        binding.subjectName.setText(list.subject.name);
        binding.exercisesCount.setText("Liczba zadań: "+list.exercises.size());
        binding.gradeValue.setText("Ocena: "+list.grade);
        binding.listNumber.setText("Lista "+list.listNumber);
    }
}
