package com.narawit.todo.base.scene

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    protected val _error: MediatorLiveData<Throwable> = MediatorLiveData()
    val error: LiveData<Throwable> = _error
    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler + Dispatchers.IO + Dispatchers.Default + Dispatchers.Unconfined

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            _isLoading.postValue(false)
            _error.postValue(throwable)
        }

}