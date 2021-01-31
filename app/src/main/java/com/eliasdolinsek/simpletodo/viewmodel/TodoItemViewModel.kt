package com.eliasdolinsek.simpletodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eliasdolinsek.simpletodo.domain.Repository
import com.eliasdolinsek.simpletodo.domain.TodoItem
import java.util.*

class TodoItemViewModel(private val repository: Repository) : ViewModel() {

    private val _todoItems = MutableLiveData<List<TodoItem>>()
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    private fun loadTodoItems() {
        _todoItems.value = repository.getAll()
    }

    init {
        loadTodoItems()
    }

    fun addNewTodoItem(name: String, description: String, deadline: Date) {
        repository.add(TodoItem(name, description, deadline, done = false))
        loadTodoItems()
    }

    fun updateTodoItem(id: UUID, name: String, description: String, deadline: Date) {
        val todoItem = repository.getById(id)
        todoItem?.let { it ->
            it.copy(name = name, description = description, deadline = deadline).also {
                repository.update(id, it)
            }
        }
    }

    fun updateDone(id: UUID) {
        val todoItem = repository.getById(id)
        todoItem?.let { it -> it.copy(done = !todoItem.done).also { repository.update(id, it) } }
    }

    fun remove(id: UUID) = repository.remove(id)

}