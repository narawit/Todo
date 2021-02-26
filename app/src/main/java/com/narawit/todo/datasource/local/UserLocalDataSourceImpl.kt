package com.narawit.todo.datasource.local

import com.narawit.todo.data.datasource.UserLocalDataSource
import com.narawit.todo.datasource.model.UserEntity

class UserLocalDataSourceImpl(
    private val local: UserDao
) : UserLocalDataSource {
    override suspend fun get(): List<UserEntity> {
        return local.get()
    }

    override suspend fun clear() {
        local.clearData()
    }

    override suspend fun insert(vararg data: UserEntity) {
        local.insert(*data)
    }

    override suspend fun delete(vararg data: UserEntity) {
        local.delete(*data)
    }

    override suspend fun update(vararg data: UserEntity) {
        local.update(*data)
    }
}