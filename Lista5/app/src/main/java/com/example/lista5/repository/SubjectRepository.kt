package com.example.lista5.repository

import com.example.lista5.data.Subject
import com.example.lista5.data.SubjectDao

class SubjectRepository(private val subjectDao: SubjectDao) {
    fun getUsers() = subjectDao.getSubjects()
    suspend fun clear() = subjectDao.deleteAll()
    suspend fun add(subject: Subject) = subjectDao.insert(subject)
    suspend fun delete(id: Int) = subjectDao.delete(id)
    suspend fun update(name: String, grade: Float, index: Int) = subjectDao.update(name, grade, index)
}