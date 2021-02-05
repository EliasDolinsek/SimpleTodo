package com.eliasdolinsek.simpletodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eliasdolinsek.simpletodo.domain.Repository
import com.eliasdolinsek.simpletodo.domain.TodoItem
import java.util.*

class OverviewViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OverviewViewModel(repository) as T
    }
}

class OverviewViewModel(private val repository: Repository) : ViewModel() {

    private val todoItems = MutableLiveData<List<TodoItem>>()

    fun loadTodoItems() {
        todoItems.value = repository.getAll()
    }

    fun getAllTodoItems(): LiveData<List<TodoItem>> = todoItems.also { loadTodoItems() }

    fun updateDone(id: UUID) {
        val todoItem = repository.getById(id)
        todoItem?.let { it -> it.copy(done = !todoItem.done).also { repository.update(id, it) } }
    }

}