package com.eliasdolinsek.simpletodo.models

import java.util.*

data class TodoItem(
    val id: UUID,
    val name: String,
    val description: String,
    val deadline: Date,
    val done: Boolean
)