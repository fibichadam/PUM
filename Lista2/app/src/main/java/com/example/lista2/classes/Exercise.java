package com.example.lista2.classes;

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum;

import java.util.Random;

public class Exercise {
    public String content;
    public int points;
    public int exerciseNumber;
    public Exercise(int exerciseNumber){
        content = new LoremIpsum(new Random().nextInt(30) + 15).getValues().iterator().next();
        points = new Random().nextInt(10) + 1;
        this.exerciseNumber = exerciseNumber;
    }
}
