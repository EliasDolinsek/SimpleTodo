package com.eliasdolinsek.simpletodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eliasdolinsek.simpletodo.databinding.FragmentOverviewBinding
import com.eliasdolinsek.simpletodo.domain.TodoItem
import com.eliasdolinsek.simpletodo.list.TodoItemAdapter
import java.util.*

class OverviewFragment : Fragment() {

    private lateinit var todoItemAdapter: TodoItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)

        todoItemAdapter =
            TodoItemAdapter(
                listOf(
                    TodoItem("Test", "Test", Date(), false),
                    TodoItem("Test", "", Date(), false)
                ),
                onEdit = ::onEditTodoItem,
                onDoneChanged = ::onCheckTodoItem
            )

        binding.rvOverviewTodoItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoItemAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        return binding.root
    }

    private fun onEditTodoItem(id: UUID) {
        val action =
            OverviewFragmentDirections.actionOverviewFragmentToEditorFragment(id.toString())
        findNavController().navigate(action)
    }

    private fun onCheckTodoItem(id: UUID) {

    }

}