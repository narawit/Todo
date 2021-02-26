package com.narawit.todo.data.datasource

import com.narawit.todo.base.data.BaseResponse
import com.narawit.todo.base.data.remote.RemoteDataSource
import com.narawit.todo.datasource.model.UserEntity

interface UserRemoteDataSource : RemoteDataSource<UserEntity> {
    suspend fun login(user: UserEntity): BaseResponse<UserEntity>
    suspend fun register(user: UserEntity): BaseResponse<UserEntity>
    suspend fun logout(): BaseResponse<Any>
}