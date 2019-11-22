package br.com.fornaro.domain.model

import java.util.*

data class TodoItem(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val dueDate: Date? = null,
    val remainder: Date? = null
)