package com.semicolon.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.todoapp.data.TodoEntity
import com.semicolon.todoapp.databinding.ListItemTodoBinding
import com.semicolon.todoapp.ui.fragments.ToDoListFragmentDirections

/**
 *Created by Hassan Mohammed on 7/18/21
 */
class TodoAdapter :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private var todos: List<TodoEntity> = emptyList()
    fun setTodos(todos: List<TodoEntity>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    class TodoViewHolder(private val binding: ListItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun getViewHolder(parent: ViewGroup): TodoViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemTodoBinding.inflate(inflater, parent, false)
                return TodoViewHolder(binding)
            }
        }

        fun bind(item: TodoEntity) {
            binding.todo = item
            binding.executePendingBindings()
            itemView.setOnClickListener {
                val action =
                    ToDoListFragmentDirections.actionToDoListFragmentToUpdateTodoFragment(item)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder.getViewHolder(parent)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) =
        holder.bind(todos[position])

    override fun getItemCount(): Int = todos.size
}