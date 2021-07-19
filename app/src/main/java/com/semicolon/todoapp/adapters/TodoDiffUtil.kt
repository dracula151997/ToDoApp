package com.semicolon.todoapp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.semicolon.todoapp.data.TodoEntity

/**
 *Created by Hassan Mohammed on 7/19/21
 */
class TodoDiffUtil : DiffUtil.ItemCallback<TodoEntity>() {
    override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem == newItem
    }
}