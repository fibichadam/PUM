package com.example.lista2.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ExerciseList {
    public ArrayList<Exercise> exercises;
    public Subject subject;
    public Float grade;
    public int listNumber;

    public ExerciseList() {
        exercises = new ArrayList<>();
        int exercisesCount = new Random().nextInt(10) + 1;
        for (int e = 0; e < exercisesCount; e++)
            exercises.add(new Exercise(e+1));

        subject = new Subject();
        grade = Arrays.asList(3f, 3.5f, 4f, 4.5f, 5f).get(new Random().nextInt(5));
    }
}
