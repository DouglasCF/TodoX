package br.com.fornaro.domain.repository

import br.com.fornaro.domain.database.dao.TodoItemDao
import br.com.fornaro.domain.model.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoItemRepository(
    private val dao: TodoItemDao
) {

    suspend fun insert(todoItem: TodoItem) = withContext(Dispatchers.IO) {
        dao.insert(todoItem)
    }

    fun selectAll() = dao.selectAll()
}