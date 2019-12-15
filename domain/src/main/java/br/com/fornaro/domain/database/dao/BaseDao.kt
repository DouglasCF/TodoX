package br.com.fornaro.domain.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    suspend fun insert(t: T): Long

    @Insert
    suspend fun insert(list: List<T>): List<Long>

    @Delete
    suspend fun delete(t: T)

    @Update
    suspend fun update(t: T)
}