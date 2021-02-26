package com.narawit.todo.base.data

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("success") val success:Boolean = false,
    @SerializedName("message") val message:String? = null,
    @SerializedName("data") val data : T? = null
) {
    companion object {
        fun <T> success(data: T?): BaseResponse<T> = BaseResponse(true, null, data)
        fun <T> error(message: String?): BaseResponse<T> = BaseResponse(false, message, null)
    }
}
