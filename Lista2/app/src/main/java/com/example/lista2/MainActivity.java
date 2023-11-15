package com.example.lista2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.lista2.classes.ExerciseList;
import com.example.lista2.classes.SubjectInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    public static ArrayList<ExerciseList> exerciseLists = new ArrayList<>();
    public static ArrayList<SubjectInfo> subjectInfos = new ArrayList<>();

    private void createExercises()
    {
        for(int i = 0; i < 20; i++) {
            exerciseLists.add(new ExerciseList());
            int j;
            boolean containsSubject = false;
            for(j = 0; j < subjectInfos.size(); j++) {
                if(subjectInfos.get(j).subject.name.equals(exerciseLists.get(i).subject.name)) {
                    subjectInfos.get(j).listCount++;
                    subjectInfos.get(j).averageGrade += exerciseLists.get(i).grade;
                    containsSubject = true;
                }
            }

            if(!containsSubject) {
                subjectInfos.add(new SubjectInfo(exerciseLists.get(i).subject));
                subjectInfos.get(j).averageGrade = exerciseLists.get(i).grade;
            }
        }

        for(int i = 0; i < subjectInfos.size(); i++) {
            subjectInfos.get(i).averageGrade = (float) Math.round(2 * subjectInfos.get(i).averageGrade / subjectInfos.get(i).listCount) / 2;
        }

        exerciseLists.sort(Comparator.comparing(o -> o.subject.name));
        int listNumber = 1;
        exerciseLists.get(0).listNumber = 1;
        for(int i = 1; i < exerciseLists.size(); i++)
        {
            if(!exerciseLists.get(i).subject.name.equals(exerciseLists.get(i - 1).subject.name))
                exerciseLists.get(i).listNumber = listNumber = 1;
            else
                exerciseLists.get(i).listNumber = ++listNumber;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        createExercises();
    }
}