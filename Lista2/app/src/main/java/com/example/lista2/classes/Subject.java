package com.example.lista2.classes;

import java.util.Arrays;
import java.util.Random;

public class Subject {
    public String name;

    public Subject() {
        name = Arrays.asList("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy").get(new Random().nextInt(5));
    }
}
