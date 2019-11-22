package br.com.fornaro.domain.usecase

import br.com.fornaro.domain.model.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeUseCases {

    suspend fun getTodoItems() = withContext(Dispatchers.IO) {
        if (false) throw Exception("Error loading todo items")
        listOf(
            TodoItem(1, "Fazer Tal", "Fazer tal lá em tal")
        )
    }
}