package com.eliasdolinsek.simpletodo.viewmodel

import android.util.Log
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

    var newTodoItem: Boolean? = null

    val todoItem: TodoItem by lazy {
        id?.let {
            newTodoItem = false
            repository.getById(it)
        } ?: run {
            newTodoItem = true
            TodoItem()
        }
    }

    fun setTodoItem(
        name: String? = null,
        deadline: Calendar? = null,
        description: String? = null
    ) {
        id?.let {
            repository.update(
                it,
                todoItem.copy(
                    name = name ?: todoItem.name,
                    deadline = deadline ?: todoItem.deadline,
                    description = description ?: todoItem.description
                )
            )
        } ?: run {
            repository.add(
                TodoItem(
                    name = name ?: "",
                    description = description ?: "",
                    deadline = deadline
                )
            )
        }
    }

    fun removeTodoItem(id: UUID) = repository.remove(id)

}