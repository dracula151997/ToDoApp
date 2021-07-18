package com.semicolon.todoapp.utils

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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

        @BindingAdapter("convertPriorityToPosition")
        @JvmStatic
        fun convertPriorityToPosition(spinner: AutoCompleteTextView, priority: Priority) {
            println("Priority ${spinner.adapter}")
            val priorityName = spinner.adapter?.getItem(getPriorityPosition(priority)) as? String
            spinner.setText(priorityName, false)
        }

        @BindingAdapter("setSpinnerList")
        @JvmStatic
        fun setSpinnerList(spinner: AutoCompleteTextView, items: List<String>) {
            val adapter =
                ArrayAdapter(spinner.context, android.R.layout.simple_list_item_1, items)
            spinner.setAdapter(adapter)
        }


        private fun getPriorityPosition(priority: Priority): Int {
            return when (priority) {
                Priority.LOW -> 0
                Priority.MEDIUM -> 1
                Priority.HIGH -> 2
            }
        }
    }
}