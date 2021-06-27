package com.semicolon.todoapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@Dao
interface TodoDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertTodo(item: TodoEntity)

    @Query("SELECT * FROM todos ORDER BY id ASC")
    fun getTodos(): Flow<TodoEntity>
}