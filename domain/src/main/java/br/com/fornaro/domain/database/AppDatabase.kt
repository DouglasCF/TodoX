package br.com.fornaro.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fornaro.domain.database.converters.DateConverter
import br.com.fornaro.domain.database.dao.TodoItemDao
import br.com.fornaro.domain.model.TodoItem

@Database(entities = [TodoItem::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao
}