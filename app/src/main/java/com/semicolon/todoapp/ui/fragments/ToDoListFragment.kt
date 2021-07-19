package com.semicolon.todoapp.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.semicolon.todoapp.R
import com.semicolon.todoapp.adapters.TodoAdapter
import com.semicolon.todoapp.data.TodoEntity
import com.semicolon.todoapp.databinding.FragmentToDoListBinding
import com.semicolon.todoapp.repo.MainViewModel
import com.semicolon.todoapp.ui.BaseFragment
import com.semicolon.todoapp.utils.SwipeToDelete
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToDoListFragment : BaseFragment<FragmentToDoListBinding>(R.layout.fragment_to_do_list) {
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { TodoAdapter() }
    override fun onViewCreated() {
        binding.todoAdapter = adapter
        registerSwipeToDelete()
    }

    override fun setListenersForViews() {
        binding.addFab.setOnClickListener { findNavController().navigate(R.id.action_toDoListFragment_to_addTodoFragment) }
    }

    override fun subscribeLiveDataObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.readTodos.collect { todos ->
                    binding.emptyTodo = todos.isEmpty()
                    adapter.setTodos(todos)
                }
            }

        }
    }

    override fun setOptionMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all) {
            deleteAllTodos()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllTodos() {
        viewModel.deleteAllTodos()
    }

    private fun registerSwipeToDelete() {
        val swipeToDeleteListener = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todo = adapter.getTodo(viewHolder.adapterPosition)
                viewModel.deleteTodo(todo)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedTodo(viewHolder.itemView, todo, viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteListener)
        itemTouchHelper.attachToRecyclerView(binding.todosRecycler)
    }

    private fun restoreDeletedTodo(view: View, deletedTodo: TodoEntity, position: Int) {
        val snackbar = Snackbar.make(
            view,
            "Deleted '${deletedTodo.title}'",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Undo") {
            viewModel.insertTodo(deletedTodo)
            adapter.notifyItemChanged(position)
        }
        snackbar.show()
    }


}