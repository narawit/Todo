package com.narawit.todo.repository

import com.narawit.todo.datasource.model.UserEntity

interface UserRepository {
    suspend fun login(user: UserEntity): Boolean
    suspend fun register(user: UserEntity): Boolean
    suspend fun logout(): Boolean
    suspend fun get(): List<UserEntity>
}