package com.narawit.todo.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.narawit.todo.base.data.local.BaseDao
import com.narawit.todo.datasource.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseDao<TaskEntity> {
    @Query("select * from task order by createdAt desc")
    suspend fun get(): List<TaskEntity>

    @Query("select * from task where id in (:id)")
    suspend fun getByIds(vararg id: String): List<TaskEntity>

    @Query("select * from task where completed == :completed order by createdAt desc")
    suspend fun getByCompleted(completed: Boolean): List<TaskEntity>

    @Query("delete from task")
    suspend fun clearData()
}