package com.narawit.todo.datasource.remote

import com.narawit.todo.base.data.BaseResponse
import com.narawit.todo.datasource.model.UserEntity
import com.narawit.todo.datasource.model.UserResponse
import com.narawit.todo.utils.Constants
import com.orhanobut.hawk.Hawk
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user/register")
    suspend fun register(@Body request: UserEntity): UserResponse

    @POST("/user/login")
    suspend fun login(@Body request: UserEntity): UserResponse

    @POST("/user/logout")
    suspend fun logout(@Header("Authorization") token:String= "Bearer ${Hawk.get(Constants.TOKEN, "")}"): BaseResponse<Any>

}