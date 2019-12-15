package br.com.fornaro.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val dueDate: Date? = null,
    val remainder: Date? = null
)