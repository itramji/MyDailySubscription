package com.bornbytes.mydailysubscription.repository

import com.bornbytes.mydailysubscription.api.BaseApi

class OrderRepository(val api: BaseApi): BaseRepository() {

    suspend fun getOrders() = safeRemoteCall { api.getOrders() }
}