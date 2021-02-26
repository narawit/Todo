package com.narawit.todo.scene.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.narawit.todo.base.scene.BaseViewModel
import com.narawit.todo.datasource.model.TaskEntity
import com.narawit.todo.repository.TaskRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val task: TaskRepository) : BaseViewModel() {
    private val _data: MutableLiveData<List<TaskEntity>> = MutableLiveData()
    val data: LiveData<List<TaskEntity>> = _data

    init {
        get()
    }

    fun get() {
        launch {
            _isLoading.postValue(true)
            val result = task.get()
            _data.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun refresh() {
        launch {
            val result = task.get()
            _data.postValue(result)
        }
    }

    fun create(data: TaskEntity) {
        launch {
            _isLoading.postValue(true)
            val result = task.createTask(data)
            _data.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun update(data: TaskEntity) {
        launch {
            _isLoading.postValue(true)
            val result = task.updateTask(data)
            _data.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun delete(data: TaskEntity) {
        launch {
            _isLoading.postValue(true)
            val result = task.deleteTask(data)
            _data.postValue(result)
            _isLoading.postValue(false)
        }
    }
}