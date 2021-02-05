package com.eliasdolinsek.simpletodo.domain

import java.util.*

data class TodoItem(
    val name: String = "",
    val description: String = "",
    val deadline: Calendar? = null,
    val done: Boolean = false,
    val id: UUID = UUID.randomUUID()
)

