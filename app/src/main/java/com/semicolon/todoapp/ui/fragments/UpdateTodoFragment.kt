package com.semicolon.todoapp.ui.fragments

import com.semicolon.todoapp.R
import com.semicolon.todoapp.databinding.FragmentUpdateTodoBinding
import com.semicolon.todoapp.ui.BaseFragment

class UpdateTodoFragment : BaseFragment<FragmentUpdateTodoBinding>(R.layout.fragment_update_todo) {
    override fun setOptionMenu(): Boolean {
        return false
    }

    override fun onViewCreated() {
    }

    override fun setListenersForViews() {
    }

    override fun subscribeLiveDataObservers() {
    }
}