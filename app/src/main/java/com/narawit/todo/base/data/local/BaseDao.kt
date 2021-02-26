package com.narawit.todo.base.data.local

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data: T)

    @Delete
    suspend fun delete(vararg data: T)

    @Update
    suspend fun update(vararg data: T)
}