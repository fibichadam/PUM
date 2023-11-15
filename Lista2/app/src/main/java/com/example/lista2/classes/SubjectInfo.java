package com.example.lista2.classes;

public class SubjectInfo {
    public Subject subject;
    public float averageGrade;
    public int listCount;

    public SubjectInfo(Subject subject)
    {
        this.subject = subject;
        averageGrade = 0;
        listCount = 1;
    }
}
