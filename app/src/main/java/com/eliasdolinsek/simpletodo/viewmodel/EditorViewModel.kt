package com.eliasdolinsek.simpletodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eliasdolinsek.simpletodo.domain.Repository
import com.eliasdolinsek.simpletodo.domain.TodoItem
import java.util.*

class EditorViewModelFactory(private val id: UUID?, private val repository: Repository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditorViewModel(id, repository) as T
    }
}

class EditorViewModel(private val id: UUID?, private val repository: Repository) : ViewModel() {

    val todoItem: TodoItem by lazy {
        id?.let { repository.getById(it) } ?: TodoItem()
    }

    fun updateTodoItem(name: String, deadline: Calendar?, description: String) {
        id?.let {
            repository.update(
                it,
                todoItem.copy(name = name, deadline = deadline, description = description)
            )
        }
    }

}