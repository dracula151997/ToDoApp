package com.semicolon.todoapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 *Created by Hassan Mohammed on 6/26/21
 */
@Entity(tableName = "todos")
@Parcelize
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
) : Parcelable