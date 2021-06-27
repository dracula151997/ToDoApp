package com.semicolon.todoapp.ui.fragments

import android.graphics.Color
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.semicolon.todoapp.R
import com.semicolon.todoapp.data.Priority
import com.semicolon.todoapp.data.TodoEntity
import com.semicolon.todoapp.databinding.FragmentAddTodoBinding
import com.semicolon.todoapp.repo.MainViewModel
import com.semicolon.todoapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : BaseFragment<FragmentAddTodoBinding>(R.layout.fragment_add_todo) {
    private val viewModel by viewModels<MainViewModel>()
    private var priority: String = ""
    override fun onViewCreated() {
        val priorityItems = listOf("Low", "Medium", "High")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            priorityItems
        )
        (binding.priorityTextField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

    }

    override fun setListenersForViews() {
        binding.prioritySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    priority = parent?.selectedItem.toString()
                    val spinnerTextView = parent?.getChildAt(0) as TextView
                    changePriorityTextColor(spinnerTextView, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    private fun changePriorityTextColor(spinnerTextView: TextView, position: Int) {
        when (position) {
            0 -> spinnerTextView.setTextColor(Color.RED)
            1 -> spinnerTextView.setTextColor(Color.YELLOW)
            2 -> spinnerTextView.setTextColor(Color.GREEN)
        }

    }

    override fun subscribeLiveDataObservers() {
    }

    override fun setOptionMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_todo_add) {
            insertNewTodoDB()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertNewTodoDB() {
        val title = binding.title.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val validation = validateTodoData(title, description)
        if (validation) {
            val todoEntity = TodoEntity(
                0,
                title,
                parsePriority(priority),
                description
            )
            viewModel.insertTodo(todoEntity)
            Toast.makeText(requireContext(), "Todo Added successfully!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

    }

    private fun parsePriority(priority: String): Priority {
        return when (priority) {
            "Low" -> Priority.LOW
            "Medium" -> Priority.MEDIUM
            "High" -> Priority.HIGH
            else -> Priority.LOW
        }
    }

    private fun validateTodoData(title: String, description: String): Boolean {
        if (title.isEmpty()) {
            binding.titleInputLayout.error = getString(R.string.required)
            return false
        }

        if (description.isEmpty()) {
            binding.descriptionTextField.error = getString(R.string.required)
            return false
        }

        return true
    }


}