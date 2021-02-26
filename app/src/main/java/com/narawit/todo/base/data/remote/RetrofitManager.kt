package com.narawit.todo.base.data.remote

import com.narawit.todo.BuildConfig
import com.narawit.todo.utils.Constants.CONNECT_TIME_OUT
import com.narawit.todo.utils.Constants.DEFAULT_CONNECT_TIME_OUT
import com.narawit.todo.utils.Constants.DEFAULT_READ_TIME_OUT
import com.narawit.todo.utils.Constants.DEFAULT_WRITE_TIME_OUT
import com.narawit.todo.utils.Constants.READ_TIME_OUT
import com.narawit.todo.utils.Constants.WRITE_TIME_OUT
import com.orhanobut.hawk.Hawk
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    inline fun <reified T> getService(
        baseUrl: String,
        auth: Authenticator? = null,
        interceptors: List<Interceptor>? = null
    ): T = getRetrofitBuilder(baseUrl, auth, interceptors).create(T::class.java)

    fun getRetrofitBuilder(
        baseUrl: String,
        auth: Authenticator?,
        interceptors: List<Interceptor>?
    ): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(
                GsonConverterFactory.create(
//                    GsonBuilder()
//                        .registerTypeAdapter(Date::class.java, DateSerializer())
//                        .registerTypeAdapter(Date::class.java, DateDeserializer())
//                        .create()
                )
            )
            client(okHttp(auth, interceptors))
            baseUrl(baseUrl)
        }.build()
    }

    private fun okHttp(
        auth: Authenticator?,
        interceptors: List<Interceptor>?
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (auth != null) authenticator(auth)
            interceptors?.forEach { interceptor ->
                addInterceptor(interceptor)
            }
            addInterceptor(loggingInterceptor())
            connectTimeout(Hawk.get(CONNECT_TIME_OUT, DEFAULT_CONNECT_TIME_OUT), TimeUnit.SECONDS)
            readTimeout(Hawk.get(READ_TIME_OUT, DEFAULT_READ_TIME_OUT), TimeUnit.SECONDS)
            writeTimeout(Hawk.get(WRITE_TIME_OUT, DEFAULT_WRITE_TIME_OUT), TimeUnit.SECONDS)
        }.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }


}