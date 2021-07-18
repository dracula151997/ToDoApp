package com.semicolon.todoapp.repo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.todoapp.data.TodoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    application: Application
) : AndroidViewModel(application) {
    val readTodos = repository.getTodos

    fun insertTodo(todoEntity: TodoEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTodo(todoEntity)
    }

    fun updateTodo(todoEntity: TodoEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTodo(todoEntity)
    }

    fun deleteTodo(todoEntity: TodoEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTodo(todoEntity)
    }

    fun deleteAllTodos() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllTodos()
    }
}