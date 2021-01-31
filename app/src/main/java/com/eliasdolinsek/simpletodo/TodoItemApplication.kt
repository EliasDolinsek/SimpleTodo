package com.eliasdolinsek.simpletodo

import android.app.Application
import android.content.Context
import com.eliasdolinsek.simpletodo.domain.Repository

class TodoItemApplication : Application() {
    val repository: Repository by lazy {
        Repository(getSharedPreferences("TodoItemApplication", Context.MODE_PRIVATE))
    }
}