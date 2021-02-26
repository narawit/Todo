package com.narawit.todo.data.datasource

import com.narawit.todo.base.data.local.LocalDataSource
import com.narawit.todo.datasource.model.UserEntity

interface UserLocalDataSource : LocalDataSource<UserEntity> {
    suspend fun get(): List<UserEntity>
    suspend fun clear()
}