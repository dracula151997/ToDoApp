package com.semicolon.todoapp.data

import androidx.room.TypeConverter

/**
 *Created by Hassan Mohammed on 6/26/21
 */
class TypeConverter {
    @TypeConverter
    fun fromPriority(priority: Priority) = priority.name

    @TypeConverter
    fun toPriority(priority: String) = Priority.valueOf(priority)
}