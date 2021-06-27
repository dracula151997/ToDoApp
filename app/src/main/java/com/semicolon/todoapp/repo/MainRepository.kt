package com.semicolon.todoapp.repo

import com.semicolon.todoapp.data.TodoDao
import com.semicolon.todoapp.data.TodoEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@Singleton
class MainRepository @Inject constructor(
    private val todoDao: TodoDao
) {
    val getTodos = todoDao.getTodos()

    suspend fun insertTodo(todoEntity: TodoEntity){
        todoDao.insertTodo(todoEntity)
    }
}