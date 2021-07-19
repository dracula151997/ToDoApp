package com.semicolon.todoapp.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
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
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToDoListFragment : BaseFragment<FragmentToDoListBinding>(R.layout.fragment_to_do_list),
    SearchView.OnQueryTextListener {
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { TodoAdapter() }
    override fun onViewCreated() {
        binding.todoAdapter = adapter
        registerSwipeToDelete()
        binding.todosRecycler.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 200
        }
    }

    override fun setListenersForViews() {
        binding.addFab.setOnClickListener { findNavController().navigate(R.id.action_toDoListFragment_to_addTodoFragment) }
    }

    override fun subscribeLiveDataObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.readTodos.collect { todos ->
                    binding.emptyTodo = todos.isEmpty()
                    adapter.submitList(todos)
                }
            }

        }
    }

    override fun setOptionMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo_list, menu)
        val menuSearch = menu.findItem(R.id.menu_search)
        val searchView = menuSearch.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> {
                deleteAllTodos()
            }
            R.id.menu_priority_high -> {
                viewModel.sortByHighPriority.observe(this, { todos ->
                    adapter.submitList(todos)
                })
            }
            R.id.menu_priority_medium -> {
                viewModel.sortByMediumPriority.observe(this, { todos ->
                    adapter.submitList(todos)
                })
            }
            R.id.menu_priority_low -> {
                viewModel.sortByLowPriority.observe(this, { todos ->
                    adapter.submitList(todos)
                })
            }
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
                restoreDeletedTodo(viewHolder.itemView, todo)
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteListener)
        itemTouchHelper.attachToRecyclerView(binding.todosRecycler)
    }

    private fun restoreDeletedTodo(view: View, deletedTodo: TodoEntity) {
        val snackbar = Snackbar.make(
            view,
            "Deleted '${deletedTodo.title}'",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Undo") {
            viewModel.insertTodo(deletedTodo)
        }
        snackbar.show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query.isNullOrEmpty()) return false
        searchForTodo(query)
        return true
    }

    private fun searchForTodo(query: String) {
        var searchQuery = query
        searchQuery = "%$searchQuery%"
        viewModel.search(searchQuery).observe(this, { todos ->
            binding.emptyTodo = todos.isEmpty()
            adapter.submitList(todos)
        })
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) return false
        searchForTodo(newText)
        return true
    }


}