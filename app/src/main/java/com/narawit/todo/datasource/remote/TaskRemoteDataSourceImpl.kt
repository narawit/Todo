package com.narawit.todo.datasource.remote

import com.narawit.todo.base.data.BaseResponse
import com.narawit.todo.data.datasource.TaskRemoteDataSource
import com.narawit.todo.datasource.model.TaskEntity
import com.narawit.todo.datasource.model.TaskRequest

class TaskRemoteDataSourceImpl(
    private val remote: TaskService
) : TaskRemoteDataSource {
    override suspend fun createTask(task: TaskEntity): BaseResponse<TaskEntity> {
        val result = remote.createTask(
            TaskRequest(
                completed = task.completed,
                description = task.description
            )
        )
        return if (result.success) BaseResponse.success(result.data)
        else BaseResponse.error(result.message)
    }

    override suspend fun get(): BaseResponse<List<TaskEntity>> {
        val result = remote.get()
        return if (result.data != null) BaseResponse.success(result.data)
        else BaseResponse.error(result.message)
    }

    override suspend fun getById(id: String): BaseResponse<TaskEntity> {
        val result = remote.getById(id)
        return if (result.success) BaseResponse.success(result.data)
        else BaseResponse.error(result.message)
    }

    override suspend fun getByCompleted(completed: Boolean): BaseResponse<List<TaskEntity>> {
        val result = remote.getByCompleted(completed)
        return if (result.success) BaseResponse.success(result.data)
        else BaseResponse.error(result.message)
    }

    override suspend fun updateTask(task: TaskEntity): BaseResponse<TaskEntity> {
        val result = remote.updateTask(
            task.id ?: "",
            TaskRequest(completed = task.completed, description = task.description)
        )
        return if (result.success) BaseResponse.success(result.data)
        else BaseResponse.error(result.message)
    }

    override suspend fun deleteTask(task: TaskEntity): BaseResponse<Any> {
        val result = remote.deleteTask(task.id ?: "")
        return if (result.success) BaseResponse.success(result.data)
        else BaseResponse.error(result.message)
    }
}