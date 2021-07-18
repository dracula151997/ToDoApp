package com.semicolon.todoapp.listeners

import com.semicolon.todoapp.data.TodoEntity

/**
 *Created by Hassan Mohammed on 7/18/21
 */
interface OnTodoClickListener {
    fun onTodoClicked(todo: TodoEntity)
}