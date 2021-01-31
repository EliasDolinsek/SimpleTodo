package com.eliasdolinsek.simpletodo.domain

import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

private const val TODO_ITEMS_KEY = "todoItems"

class Repository(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson = Gson()
) {

    fun add(todoItem: TodoItem) {
        val todoItems = getAll()
        todoItems.add(todoItem)
        write(todoItems)
    }

    fun remove(id: UUID) {
        val todoItems = getAll()
        todoItems.removeIf { it.id == id }
        write(todoItems)
    }

    fun update(id: UUID, update: TodoItem) {
        val todoItems = getAll()
        todoItems.indexOfFirst { it.id == id }.let {
            todoItems.removeAt(it)
            todoItems.add(it, update.copy(id = id))
        }

        write(todoItems)
    }

    private fun write(todoItems: List<TodoItem>) {
        val todoItemsJson = gson.toJson(todoItems)
        sharedPreferences.edit().putString(TODO_ITEMS_KEY, todoItemsJson).apply()
    }

    fun getAll(): MutableList<TodoItem> {
        val todoItemsStr = sharedPreferences.getString(TODO_ITEMS_KEY, null)
        todoItemsStr?.apply {
            return gson.fromJson(todoItemsStr, List::class.java)
                .toMutableList() as MutableList<TodoItem>
        }

        return mutableListOf()
    }

    fun getById(id: UUID): TodoItem? = getAll().firstOrNull { it.id == id }
}