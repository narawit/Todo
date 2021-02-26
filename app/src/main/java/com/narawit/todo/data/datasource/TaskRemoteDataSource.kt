package com.narawit.todo.data.datasource

import com.narawit.todo.base.data.BaseResponse
import com.narawit.todo.base.data.remote.RemoteDataSource
import com.narawit.todo.datasource.model.TaskEntity

interface TaskRemoteDataSource : RemoteDataSource<TaskEntity> {
    suspend fun createTask(task: TaskEntity): BaseResponse<TaskEntity>
    suspend fun get(): BaseResponse<List<TaskEntity>>
    suspend fun getById(id: String): BaseResponse<TaskEntity>
    suspend fun getByCompleted(completed: Boolean): BaseResponse<List<TaskEntity>>
    suspend fun updateTask(task: TaskEntity): BaseResponse<TaskEntity>
    suspend fun deleteTask(task: TaskEntity): BaseResponse<Any>
}