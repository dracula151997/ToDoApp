package com.semicolon.todoapp.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.semicolon.todoapp.R
import com.semicolon.todoapp.databinding.FragmentAddTodoBinding
import com.semicolon.todoapp.ui.BaseFragment

class AddTodoFragment : BaseFragment<FragmentAddTodoBinding>(R.layout.fragment_add_todo) {
    override fun onViewCreated() {

    }

    override fun setListenersForViews() {
    }

    override fun subscribeLiveDataObservers() {
    }

    override fun setOptionMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}