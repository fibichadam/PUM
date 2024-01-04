package com.example.lista5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject_table ORDER BY name ASC")
    fun getSubjects(): Flow<List<Subject>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subject: Subject)

    @Query("DELETE FROM subject_table WHERE id = :index")
    suspend fun delete(index: Int)

    @Query("DELETE FROM subject_table")
    suspend fun deleteAll()

    @Query("UPDATE subject_table SET name = :name, grade = :grade WHERE id = :index")
    suspend fun update(name: String, grade: Float, index: Int)
}