package com.bornbytes.mydailysubscription.api

import retrofit2.http.GET
import retrofit2.http.POST

interface BaseApi {

    @POST("/auth")
    suspend fun loginUser(): RemoteResult<Any>

    @GET("/orders")
    suspend fun getOrders(): RemoteResult<Any>
}