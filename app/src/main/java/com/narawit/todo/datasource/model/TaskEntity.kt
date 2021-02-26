package com.narawit.todo.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.narawit.todo.base.data.BaseEntity

@Entity(tableName = "task")
data class TaskEntity(
    @ColumnInfo(name = "completed")
    @SerializedName("completed")
    var completed: Boolean = false,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null,
    @ColumnInfo(name = "owner")
    @SerializedName("owner")
    val owner: String? = null
) : BaseEntity()