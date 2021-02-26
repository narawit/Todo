package com.narawit.todo.base.data.remote

import com.narawit.todo.utils.Constants
import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder().apply {
            header("content-type", "application/json")
            header("Authorization", "Bearer ${Hawk.get(Constants.TOKEN, "")}")
        }
        try {
            return chain.proceed(request.build())
        } catch (e: Exception) {
            throw e
        }
    }
}