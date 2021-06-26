package com.semicolon.todoapp.ui.fragments

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

    override fun setOptionMenu(): Boolean {
        return false
    }
}