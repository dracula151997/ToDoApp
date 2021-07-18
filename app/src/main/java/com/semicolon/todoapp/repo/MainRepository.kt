package com.semicolon.todoapp.repo

import com.semicolon.todoapp.data.TodoDao
import com.semicolon.todoapp.data.TodoEntity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Singleton

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@ActivityRetainedScoped
class MainRepository @Inject constructor(
    private val todoDao: TodoDao
) {
    val getTodos = todoDao.getTodos()

    suspend fun insertTodo(todoEntity: TodoEntity){
        todoDao.insertTodo(todoEntity)
    }

    suspend fun updateTodo(todoEntity: TodoEntity){
        todoDao.updateTodo(todoEntity)
    }

    suspend fun deleteTodo(todoEntity: TodoEntity) = todoDao.deleteTodo(todoEntity)

    suspend fun deleteAllTodos() = todoDao.deleteAllTodos()
}