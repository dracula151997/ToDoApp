package com.semicolon.todoapp.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.semicolon.todoapp.R
import com.semicolon.todoapp.adapters.TodoAdapter
import com.semicolon.todoapp.databinding.FragmentToDoListBinding
import com.semicolon.todoapp.repo.MainViewModel
import com.semicolon.todoapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ToDoListFragment : BaseFragment<FragmentToDoListBinding>(R.layout.fragment_to_do_list) {
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { TodoAdapter() }
    override fun onViewCreated() {
        binding.todoAdapter = adapter
    }

    override fun setListenersForViews() {
        binding.addFab.setOnClickListener { findNavController().navigate(R.id.action_toDoListFragment_to_addTodoFragment) }
    }

    override fun subscribeLiveDataObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.readTodos.collect { todos ->
                adapter.setTodos(todos)
            }
        }
    }

    override fun setOptionMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo_list, menu)
    }


}