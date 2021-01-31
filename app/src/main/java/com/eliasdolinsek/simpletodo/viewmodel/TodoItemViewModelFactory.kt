package com.eliasdolinsek.simpletodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eliasdolinsek.simpletodo.domain.Repository


class TodoItemViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoItemViewModel(repository) as T
    }
}