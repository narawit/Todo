package com.narawit.todo.scene

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.narawit.todo.base.scene.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    private val _bottomNavigationVisible = MutableLiveData<Boolean>()
    val bottomNavigationVisible: LiveData<Boolean> = _bottomNavigationVisible

    fun setError(throwable: Throwable) {
        _error.postValue(throwable)
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun setBottomNavigationVisible(isLoading: Boolean) {
        _bottomNavigationVisible.postValue(isLoading)
    }



    init {
        start()
    }

    fun start() {
        launch {
            _isLoading.postValue(true)
            delay(1000)

            _isLoading.postValue(false)
        }
    }
}