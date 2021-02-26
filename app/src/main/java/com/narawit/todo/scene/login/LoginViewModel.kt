package com.narawit.todo.scene.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.narawit.todo.base.scene.BaseViewModel
import com.narawit.todo.datasource.model.UserEntity
import com.narawit.todo.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val user: UserRepository) : BaseViewModel() {
    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    fun register(data: UserEntity) {
        launch {
            _isLoading.postValue(true)
            delay(1000)
            val result = user.register(data)
            _state.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun login(data: UserEntity) {
        launch {
            _isLoading.postValue(true)
            delay(1000)
            val result = user.login(data)
            _state.postValue(result)
            _isLoading.postValue(false)
        }
    }
}