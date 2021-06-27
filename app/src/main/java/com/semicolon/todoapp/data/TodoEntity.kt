package com.semicolon.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
) {
}