package com.narawit.todo.datasource.remote

import com.narawit.todo.base.data.BaseResponse
import com.narawit.todo.data.datasource.UserRemoteDataSource
import com.narawit.todo.datasource.model.UserEntity
import com.narawit.todo.utils.Constants.TOKEN
import com.orhanobut.hawk.Hawk

class UserRemoteDataSourceImpl(
    private val remote: UserService
) : UserRemoteDataSource {
    override suspend fun login(user: UserEntity): BaseResponse<UserEntity> {
        val result = remote.login(user)
        return if (!result.token.isNullOrEmpty() || result.user != null) {
            Hawk.put(TOKEN, result.token)
            BaseResponse.success(result.user)
        } else {
            BaseResponse.error("Login is failed.")
        }
    }

    override suspend fun register(user: UserEntity): BaseResponse<UserEntity> {
        val result = remote.register(user)
        return if (!result.token.isNullOrEmpty() || result.user != null) {
            Hawk.put(TOKEN, result.token)
            BaseResponse.success(result.user)
        } else {
            BaseResponse.error("Register is failed.")
        }
    }

    override suspend fun logout() = remote.logout()
}