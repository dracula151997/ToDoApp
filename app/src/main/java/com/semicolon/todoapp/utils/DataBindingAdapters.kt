package com.semicolon.todoapp.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.todoapp.R
import com.semicolon.todoapp.adapters.TodoAdapter
import com.semicolon.todoapp.data.Priority

/**
 *Created by Hassan Mohammed on 7/18/21
 */
class DataBindingAdapters {
    companion object {
        @BindingAdapter("setPriorityColor")
        @JvmStatic
        fun TextView.setPriorityColor(priority: Priority) {
            when (priority) {
                Priority.LOW -> this.setBackgroundResource(R.drawable.shape_low_priority)
                Priority.MEDIUM -> this.setBackgroundResource(R.drawable.shape_medium_priority)
                Priority.HIGH -> this.setBackgroundResource(R.drawable.shape_high_priority)
            }
        }

        @BindingAdapter("setTodoAdapter")
        @JvmStatic
        fun RecyclerView.setTodoAdapter(adapter: TodoAdapter) {
            this.adapter = adapter
        }

        @BindingAdapter("viewGone")
        @JvmStatic
        fun View.viewGone(gone: Boolean) {
            this.visibility = if (gone) GONE else VISIBLE
        }
    }
}