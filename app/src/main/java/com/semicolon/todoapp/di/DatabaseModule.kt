package com.semicolon.todoapp.di

import android.content.Context
import androidx.room.Room
import com.semicolon.todoapp.data.TodoDatabase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        "todo_db"

    ).build()

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDatabase) = database.todoDao()
}