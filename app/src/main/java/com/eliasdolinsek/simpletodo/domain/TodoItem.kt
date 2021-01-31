package com.eliasdolinsek.simpletodo.domain

import java.util.*

data class TodoItem(
    val name: String,
    val description: String,
    val deadline: Date,
    val done: Boolean,
    val id: UUID = UUID.randomUUID()
)

