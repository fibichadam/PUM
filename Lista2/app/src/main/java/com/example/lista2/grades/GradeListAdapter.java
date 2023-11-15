package com.example.lista2.grades;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.classes.SubjectInfo;
import com.example.lista2.databinding.GradesListItemBinding;

import java.util.ArrayList;

public class GradeListAdapter extends RecyclerView.Adapter<GradeListViewHolder>
{
    private final ArrayList<SubjectInfo> subjectInfos;

    public GradeListAdapter(ArrayList<SubjectInfo> subjectInfos)
    {
        this.subjectInfos = subjectInfos;
    }

    @NonNull
    @Override
    public GradeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GradeListViewHolder(GradesListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull GradeListViewHolder holder, int position) {
        holder.bind(subjectInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return subjectInfos.size();
    }
}
