package com.narawit.todo.datasource.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    val user: UserEntity? = null,
    @SerializedName("token")
    val token: String? = null
)
