package com.semicolon.todoapp.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.todoapp.R
import com.semicolon.todoapp.adapters.TodoAdapter
import com.semicolon.todoapp.databinding.FragmentToDoListBinding
import com.semicolon.todoapp.repo.MainViewModel
import com.semicolon.todoapp.ui.BaseFragment
import com.semicolon.todoapp.utils.SwipeToDelete
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

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
        lifecycleScope.launchWhenCreated {
            viewModel.readTodos.collect { todos ->
                binding.emptyTodo = todos.isEmpty()
                adapter.setTodos(todos)
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
                Toast.makeText(
                    requireContext(),
                    "Successfully removed: '${todo.title}'",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteListener)
        itemTouchHelper.attachToRecyclerView(binding.todosRecycler)
    }


}