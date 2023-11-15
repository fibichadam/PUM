package com.example.lista2.grades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista2.MainActivity;
import com.example.lista2.databinding.FragmentGradesBinding;

public class GradesFragment extends Fragment {

    private FragmentGradesBinding binding;
    public GradesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater);

        binding.recyclerView.setAdapter(new GradeListAdapter(MainActivity.subjectInfos));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return binding.getRoot();
    }

}