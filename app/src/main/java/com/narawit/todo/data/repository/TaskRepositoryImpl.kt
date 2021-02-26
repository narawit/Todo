package com.narawit.todo.data.repository

import com.narawit.todo.data.datasource.TaskLocalDataSource
import com.narawit.todo.data.datasource.TaskRemoteDataSource
import com.narawit.todo.datasource.model.TaskEntity
import com.narawit.todo.repository.TaskRepository

class TaskRepositoryImpl(
    private val local: TaskLocalDataSource,
    private val remote: TaskRemoteDataSource
) : TaskRepository {
    override suspend fun createTask(task: TaskEntity): List<TaskEntity> {
        val result = remote.createTask(task)
        if (result.success) local.insert(result.data!!)
        return local.get()
    }

    override suspend fun get(): List<TaskEntity> {
        val result = remote.get()
        if (result.success) {
            local.clear()
            local.insert(*result.data!!.toTypedArray())
        }
        return local.get()
    }

    override suspend fun getById(id: String): TaskEntity? = local.getByIds(id).firstOrNull()

    override suspend fun getByCompleted(completed: Boolean): List<TaskEntity> =
        local.getByCompleted(completed)

    override suspend fun updateTask(task: TaskEntity): List<TaskEntity> {
        val result = remote.updateTask(task)
        if (result.success) local.update(result.data!!)
        return local.get()
    }

    override suspend fun deleteTask(task: TaskEntity): List<TaskEntity> {
        val result = remote.deleteTask(task)
        if (result.success) local.delete(task)
        return local.get()
    }
}