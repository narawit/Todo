package com.narawit.todo.datasource.model

import com.google.gson.annotations.SerializedName

data class TaskRequest(
    @SerializedName("completed")
    var completed: Boolean = false,
    @SerializedName("description")
    var description: String? = null
)