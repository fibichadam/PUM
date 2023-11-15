package com.example.lista2.grades;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.classes.SubjectInfo;
import com.example.lista2.databinding.GradesListItemBinding;

public class GradeListViewHolder extends RecyclerView.ViewHolder {
    private final GradesListItemBinding binding;

    public GradeListViewHolder(GradesListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(SubjectInfo subject){
        binding.subjectName.setText(subject.subject.name);
        binding.listCount.setText("Liczba list: "+subject.listCount);
        binding.averageGrades.setText("Średnia: "+subject.averageGrade);
    }

}
