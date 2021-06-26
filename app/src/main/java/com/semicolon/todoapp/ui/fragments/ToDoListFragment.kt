package com.semicolon.todoapp.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.semicolon.todoapp.R
import com.semicolon.todoapp.databinding.FragmentToDoListBinding
import com.semicolon.todoapp.ui.BaseFragment

class ToDoListFragment : BaseFragment<FragmentToDoListBinding>(R.layout.fragment_to_do_list) {
    override fun onViewCreated() {
    }

    override fun setListenersForViews() {
        binding.addFab.setOnClickListener { findNavController().navigate(R.id.action_toDoListFragment_to_addTodoFragment) }
    }

    override fun subscribeLiveDataObservers() {
    }

    override fun setOptionMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}