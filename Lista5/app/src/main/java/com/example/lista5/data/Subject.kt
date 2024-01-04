package com.example.lista5.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
data class Subject(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val grade: Float)