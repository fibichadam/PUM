package com.example.lista2.exerciseLists;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.R;
import com.example.lista2.classes.ExerciseList;
import com.example.lista2.databinding.ExerciseListsItemBinding;

import java.util.ArrayList;

public class ExerciseListsAdapter extends RecyclerView.Adapter<ExerciseListsViewHolder> {
    private final ArrayList<ExerciseList> exerciseLists;

    public ExerciseListsAdapter(ArrayList<ExerciseList> exerciseLists)
    {
        this.exerciseLists = exerciseLists;
    }

    @NonNull
    @Override
    public ExerciseListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseListsViewHolder(ExerciseListsItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseListsViewHolder holder, int position) {
        holder.bind(exerciseLists.get(position));
        Bundle bundle = new Bundle();
        bundle.putInt("listNumber", position);
        holder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseListsFragment_to_exercisesFragment, bundle));
    }

    @Override
    public int getItemCount() {
        return exerciseLists.size();
    }
}
