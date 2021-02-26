package com.narawit.todo


import com.narawit.todo.base.data.local.AppDatabase
import com.narawit.todo.base.data.remote.RequestInterceptor
import com.narawit.todo.base.data.remote.RetrofitManager
import com.narawit.todo.data.datasource.TaskLocalDataSource
import com.narawit.todo.data.datasource.TaskRemoteDataSource
import com.narawit.todo.data.datasource.UserLocalDataSource
import com.narawit.todo.data.datasource.UserRemoteDataSource
import com.narawit.todo.data.repository.TaskRepositoryImpl
import com.narawit.todo.data.repository.UserRepositoryImpl
import com.narawit.todo.datasource.local.TaskLocalDataSourceImpl
import com.narawit.todo.datasource.local.UserLocalDataSourceImpl
import com.narawit.todo.datasource.remote.TaskRemoteDataSourceImpl
import com.narawit.todo.datasource.remote.TaskService
import com.narawit.todo.datasource.remote.UserRemoteDataSourceImpl
import com.narawit.todo.datasource.remote.UserService
import com.narawit.todo.repository.TaskRepository
import com.narawit.todo.repository.UserRepository
import com.narawit.todo.scene.home.HomeViewModel
import com.narawit.todo.scene.login.LoginViewModel
import com.narawit.todo.scene.MainViewModel
import com.narawit.todo.scene.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


fun injectApp() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(serviceModule, databaseModule, repositoryModule, viewModelModule))
}


val serviceModule: Module = module {
    single {
        RetrofitManager.getService<UserService>(
            baseUrl = BuildConfig.BASE_URL
        )
    }
    single {
        RetrofitManager.getService<TaskService>(
            baseUrl = BuildConfig.BASE_URL,
            interceptors = mutableListOf(RequestInterceptor())
        )
    }
}

val databaseModule: Module = module {
    single { AppDatabase.getDatabase(androidContext()) }
}

val repositoryModule: Module = module {
    single { UserRemoteDataSourceImpl(get()) as UserRemoteDataSource }
    single { UserLocalDataSourceImpl(get<AppDatabase>().user()) as UserLocalDataSource }
    single { TaskRemoteDataSourceImpl(get()) as TaskRemoteDataSource }
    single { TaskLocalDataSourceImpl(get<AppDatabase>().task()) as TaskLocalDataSource }

    factory { UserRepositoryImpl(get(), get(), get()) as UserRepository }
    factory { TaskRepositoryImpl(get(), get()) as TaskRepository }
}

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}