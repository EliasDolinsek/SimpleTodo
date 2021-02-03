package com.eliasdolinsek.simpletodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eliasdolinsek.simpletodo.databinding.FragmentOverviewBinding
import com.eliasdolinsek.simpletodo.domain.TodoItem
import com.eliasdolinsek.simpletodo.list.TodoItemAdapter
import com.eliasdolinsek.simpletodo.viewmodel.OverviewViewModel
import com.eliasdolinsek.simpletodo.viewmodel.OverviewViewModelFactory
import java.util.*

class OverviewFragment : Fragment() {

    private lateinit var todoItemAdapter: TodoItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)

        val factory =
            OverviewViewModelFactory((requireActivity().application as TodoItemApplication).repository)
        val viewModel = ViewModelProvider(this, factory).get(OverviewViewModel::class.java)

        todoItemAdapter =
            TodoItemAdapter(
                onEdit = ::onEditTodoItem,
                onDoneChanged = ::onCheckTodoItem
            )

        viewModel.getAllTodoItems()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { todoItems ->
                todoItems?.let {
                    if (it.isNotEmpty()) {
                        todoItemAdapter.apply {
                            items = it
                            notifyDataSetChanged()
                        }
                    } else {
                        binding.tvOverviewNoEntries.visibility = View.VISIBLE
                    }
                } ?: run { binding.tvOverviewNoEntries.visibility = View.VISIBLE }
            })

        binding.rvOverviewTodoItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoItemAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        binding.fabOverviewAdd.setOnClickListener {
            val action = OverviewFragmentDirections.actionOverviewFragmentToEditorFragment(null)
            findNavController().navigate(action)
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