package com.narawit.todo.data.repository

import com.narawit.todo.data.datasource.TaskLocalDataSource
import com.narawit.todo.data.datasource.UserLocalDataSource
import com.narawit.todo.data.datasource.UserRemoteDataSource
import com.narawit.todo.datasource.model.UserEntity
import com.narawit.todo.repository.UserRepository

class UserRepositoryImpl(
    private val localTask: TaskLocalDataSource,
    private val localUser: UserLocalDataSource,
    private val remoteUser: UserRemoteDataSource
) : UserRepository {
    override suspend fun login(user: UserEntity): Boolean {
        val result = remoteUser.login(user)
        if (result.success) {
            localTask.clear()
            localUser.clear()
            localUser.insert(result.data!!)
        }

        return result.success
    }

    override suspend fun register(user: UserEntity): Boolean {
        val result = remoteUser.register(user)
        if (result.success) {
            localTask.clear()
            localUser.clear()
            localUser.insert(result.data!!)
        }
        return result.success
    }

    override suspend fun logout(): Boolean {
        val result = remoteUser.logout()
        if (result.success) {
            localTask.clear()
            localUser.clear()
        }
        return result.success
    }

    override suspend fun get(): List<UserEntity> {
        return localUser.get()
    }
}