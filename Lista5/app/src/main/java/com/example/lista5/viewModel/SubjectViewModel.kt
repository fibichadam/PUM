package com.example.lista5.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lista5.data.Subject
import com.example.lista5.data.SubjectDatabase
import com.example.lista5.repository.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application) : ViewModel() {

    private val repository: SubjectRepository
    private var _subjectState = MutableStateFlow<List<Subject>>(emptyList())
    val subjectState: StateFlow<List<Subject>>
        get() = _subjectState


    init {
        val db = SubjectDatabase.getDatabase(application)
        val dao = db.subjectDao()
        repository = SubjectRepository(dao)

        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            repository.getUsers().collect { subjects ->
                _subjectState.value = subjects
            }
        }
    }

    fun addSubject(subject: Subject){
        viewModelScope.launch {
            repository.add(subject)
        }
    }

    fun editSubject(name: String, grade: Float, index: Int){
        viewModelScope.launch {
            repository.update(name, grade, index)
        }
    }

    fun deleteSubject(id: Int){
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    fun clear() {
        viewModelScope.launch {
            repository.clear()
        }
    }

}