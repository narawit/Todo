package com.narawit.todo.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import com.narawit.todo.base.data.BaseEntity

@Entity(tableName = "user")
data class UserEntity(
    @ColumnInfo(name = "age")
    @SerializedName("age")
    val age: Int? = null,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String? = null,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String? = null,
) : BaseEntity() {
    @Ignore
    @SerializedName("password")
    var password: String? = null

    constructor(email: String?, password: String?) : this(email = email) {
        this.password = password
    }

    constructor(age: Int?, name: String?, email: String?, password: String?) : this(age, name, email) {
        this.password = password
    }
}