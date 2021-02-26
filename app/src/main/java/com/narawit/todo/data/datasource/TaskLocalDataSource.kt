package com.narawit.todo.data.datasource

import androidx.lifecycle.LiveData
import com.narawit.todo.base.data.local.LocalDataSource
import com.narawit.todo.datasource.model.TaskEntity

interface TaskLocalDataSource : LocalDataSource<TaskEntity> {
    suspend fun get(): List<TaskEntity>
    suspend fun getByIds(vararg id: String): List<TaskEntity>
    suspend fun getByCompleted(completed: Boolean): List<TaskEntity>
    suspend fun clear()
}