package com.narawit.todo.datasource.local

import com.narawit.todo.data.datasource.TaskLocalDataSource
import com.narawit.todo.datasource.model.TaskEntity

class TaskLocalDataSourceImpl(
    private val local: TaskDao
) : TaskLocalDataSource {
    override suspend fun get(): List<TaskEntity> = local.get()

    override suspend fun getByIds(vararg id: String): List<TaskEntity> = local.getByIds(*id)

    override suspend fun getByCompleted(completed: Boolean): List<TaskEntity> =
        local.getByCompleted(completed)

    override suspend fun clear() {
        local.clearData()
    }

    override suspend fun insert(vararg data: TaskEntity) {
        local.insert(*data)
    }

    override suspend fun delete(vararg data: TaskEntity) {
        local.delete(*data)
    }

    override suspend fun update(vararg data: TaskEntity) {
        local.update(*data)
    }
}