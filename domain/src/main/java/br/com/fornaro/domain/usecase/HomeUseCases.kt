package br.com.fornaro.domain.usecase

import br.com.fornaro.domain.repository.TodoItemRepository

class HomeUseCases(
    private val repository: TodoItemRepository
) {

    fun getTodoItems() = repository.selectAll()
}