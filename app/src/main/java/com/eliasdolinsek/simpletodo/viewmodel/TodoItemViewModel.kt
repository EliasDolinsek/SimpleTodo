package com.eliasdolinsek.simpletodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eliasdolinsek.simpletodo.domain.Repository
import com.eliasdolinsek.simpletodo.domain.TodoItem
import java.util.*

class TodoItemViewModel(private val repository: Repository) : ViewModel() {

    private val todoItems by lazy {
        MutableLiveData<List<TodoItem>>().also {
            loadTodoItems()
        }
    }

    private fun loadTodoItems() {
        todoItems.value = repository.getAll()
    }

    fun getAllTodoItems(): LiveData<List<TodoItem>> = todoItems

    fun updateDone(id: UUID) {
        val todoItem = repository.getById(id)
        todoItem?.let { it -> it.copy(done = !todoItem.done).also { repository.update(id, it) } }
    }

}