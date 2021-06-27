package com.semicolon.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}