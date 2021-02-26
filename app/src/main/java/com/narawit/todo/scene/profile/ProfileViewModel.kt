package com.narawit.todo.scene.profile

import androidx.lifecycle.MutableLiveData
import com.narawit.todo.base.scene.BaseViewModel
import com.narawit.todo.datasource.model.UserEntity
import com.narawit.todo.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val user: UserRepository) : BaseViewModel() {
    private val _data: MutableLiveData<UserEntity> = MutableLiveData()
    val data: MutableLiveData<UserEntity> = _data
    private val _state: MutableLiveData<Boolean> = MutableLiveData()
    val state: MutableLiveData<Boolean> = _state

    init {
        get()
    }

    fun get() {
        launch {
            _isLoading.postValue(true)
            val result = user.get()
            _data.postValue(result.firstOrNull())
            _isLoading.postValue(false)
        }
    }

    fun logout() {
        launch {
            _isLoading.postValue(true)
            val result = user.logout()
            _state.postValue(result)
            _isLoading.postValue(false)
        }
    }

}