package com.bornbytes.mydailysubscription.api

import okhttp3.ResponseBody


sealed class RemoteResult<out T> {
    data class Success<T>(val value: T) : RemoteResult<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorMessage: ResponseBody?
    ) : RemoteResult<Nothing>()

    data class Loading(val progress: Int?) : RemoteResult<Nothing>()
}
