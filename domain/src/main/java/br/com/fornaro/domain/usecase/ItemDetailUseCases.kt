package br.com.fornaro.domain.usecase

import br.com.fornaro.domain.model.TodoItem
import br.com.fornaro.domain.repository.TodoItemRepository

class ItemDetailUseCases(
    private val repository: TodoItemRepository
) {

   suspend fun insert(todoItem: TodoItem) {
        repository.insert(todoItem)
    }
}