package com.eliasdolinsek.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditTodoItemActivity : AppCompatActivity() {
    companion object {
        const val TODO_ITEM_ID = "todoItemId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo_item)
        actionBar?.setDisplayShowHomeEnabled(true)
    }
}