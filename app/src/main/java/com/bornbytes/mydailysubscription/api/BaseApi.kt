package com.bornbytes.mydailysubscription.api

import retrofit2.http.GET
import retrofit2.http.POST

interface BaseApi {

    @POST("/auth")
    suspend fun loginUser(): Any

    @POST("/logout")
    suspend fun logoutUser(): Any

    @GET("/orders")
    suspend fun getOrders(): Any
}