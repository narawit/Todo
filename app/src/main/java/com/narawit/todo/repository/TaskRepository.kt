package com.narawit.todo.repository

import com.narawit.todo.datasource.model.TaskEntity

interface TaskRepository {
    suspend fun createTask(task: TaskEntity): List<TaskEntity>
    suspend fun get(): List<TaskEntity>
    suspend fun getById(id: String): TaskEntity?
    suspend fun getByCompleted(completed: Boolean): List<TaskEntity>
    suspend fun updateTask(task: TaskEntity):List<TaskEntity>
    suspend fun deleteTask(task: TaskEntity):List<TaskEntity>
}