package com.narawit.todo.base.data.local

interface LocalDataSource<T> {
    suspend fun insert(vararg data: T)
    suspend fun delete(vararg data: T)
    suspend fun update(vararg data: T)
}
