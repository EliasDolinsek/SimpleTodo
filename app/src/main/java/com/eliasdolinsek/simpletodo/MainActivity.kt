package com.eliasdolinsek.simpletodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eliasdolinsek.simpletodo.databinding.ActivityMainBinding
import com.eliasdolinsek.simpletodo.list.TodoItemAdapter
import com.eliasdolinsek.simpletodo.viewmodel.TodoItemViewModel
import com.eliasdolinsek.simpletodo.viewmodel.TodoItemViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TodoItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = TodoItemViewModelFactory((application as TodoItemApplication).repository)
        viewModel = ViewModelProvider(this, factory).get(TodoItemViewModel::class.java)

        binding.rvMain.layoutManager = LinearLayoutManager(this)

        val adapter = viewModel.todoItems.value?.let {
            TodoItemAdapter(
                it,
                onDoneChanged = ::onItemDoneChanged,
                onEdit = ::onItemEdit
            )
        }

        binding.rvMain.adapter = adapter

        binding.fabMainAdd.setOnClickListener {
            viewModel.addNewTodoItem(name = "Test", description = "Test", deadline = Date())
            adapter!!.items = viewModel.todoItems.value!!
            adapter.notifyDataSetChanged()
        }
    }

    private fun onItemEdit(id: UUID) {
        val intent = Intent(this, EditTodoItemActivity::class.java).apply {
            putExtra(EditTodoItemActivity.TODO_ITEM_ID, id)
        }

        startActivity(intent)
    }

    private fun onItemDoneChanged(id: UUID) = viewModel.updateDone(id)

}