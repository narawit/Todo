package com.narawit.todo.base.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

open class BaseEntity {
    @PrimaryKey
    @SerializedName("_id")
    var id: String = ""

    @SerializedName("createdAt")
    var createdAt: String? = null

    @SerializedName("updatedAt")
    var updatedAt: String? = null

    @SerializedName("__v")
    var v: Int? = null
}