package com.narawit.todo.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.narawit.todo.base.data.local.BaseDao
import com.narawit.todo.datasource.model.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("select * from user ")
    suspend fun get(): List<UserEntity>

    @Query("select * from user where id in (:id)")
    suspend fun getByIds(vararg id: Int): List<UserEntity>

    @Query("delete from user")
    suspend fun clearData()
}