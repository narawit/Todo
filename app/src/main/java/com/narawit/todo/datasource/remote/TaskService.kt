package com.narawit.todo.datasource.remote

import com.narawit.todo.base.data.BaseResponse
import com.narawit.todo.datasource.model.TaskEntity
import com.narawit.todo.datasource.model.TaskRequest
import retrofit2.http.*

interface TaskService {
    @POST("/task")
    suspend fun createTask(@Body task: TaskRequest): BaseResponse<TaskEntity>

    @GET("/task")
    suspend fun get(): BaseResponse<List<TaskEntity>>

    @GET("/task/{id}")
    suspend fun getById(@Path("id") id: String): BaseResponse<TaskEntity>

    @GET("/task")
    suspend fun getByCompleted(@Query("completed") completed: Boolean): BaseResponse<List<TaskEntity>>

//    @GET("/task")
//    suspend fun getByPagination(
//        @Query("limit") limit: Int,
//        @Query("skip") skip: Int
//    ): BaseResponse<List<TaskEntity>>

    @PUT("/task/{id}")
    suspend fun updateTask(
        @Path("id") id: String,
        @Body request: TaskRequest
    ): BaseResponse<TaskEntity>

    @DELETE("/task/{id}")
    suspend fun deleteTask(
        @Path("id") id: String
    ): BaseResponse<Any>
}