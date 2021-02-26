package com.narawit.todo.base.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.narawit.todo.datasource.local.TaskDao
import com.narawit.todo.datasource.local.UserDao
import com.narawit.todo.datasource.model.TaskEntity
import com.narawit.todo.datasource.model.UserEntity
import com.narawit.todo.utils.Constants


@Database(
    entities = [TaskEntity::class, UserEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Synchronized
        fun getDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun task(): TaskDao
    abstract fun user(): UserDao
}