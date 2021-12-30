package com.bornbytes.mydailysubscription.repository

import com.bornbytes.mydailysubscription.api.RemoteResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeRemoteCall(apiCall: suspend () -> T): RemoteResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                RemoteResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> RemoteResult.Failure(
                        false,
                        throwable.code(),
                        throwable.response()?.errorBody()
                    )
                    else -> RemoteResult.Failure(true, null, null)
                }
            }
        }
    }
}