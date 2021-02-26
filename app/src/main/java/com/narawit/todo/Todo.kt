package com.narawit.todo

import androidx.multidex.MultiDexApplication
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Todo : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        startKoin {
            androidLogger()
            androidContext(this@Todo)
        }
        injectApp()
    }
}