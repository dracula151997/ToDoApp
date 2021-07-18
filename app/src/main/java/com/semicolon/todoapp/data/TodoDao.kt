package com.semicolon.todoapp.data

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import kotlinx.coroutines.flow.Flow

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@Dao
interface TodoDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertTodo(item: TodoEntity)

    @Query("SELECT * FROM todos ORDER BY id ASC")
    fun getTodos(): Flow<List<TodoEntity>>

    @Update
    suspend fun updateTodo(todoEntity: TodoEntity)

    @Delete
    suspend fun deleteTodo(todoEntity: TodoEntity)
}