package br.com.fornaro.domain.database.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.fornaro.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao : BaseDao<TodoItem> {

    @Query("SELECT * FROM TodoItem")
    fun selectAll(): Flow<List<TodoItem>>

    @Query("SELECT * FROM TodoItem WHERE id = :todoItemId")
    fun select(todoItemId: Long): TodoItem
}