package com.bornbytes.mydailysubscription.repository

import com.bornbytes.mydailysubscription.api.BaseApi

class AuthRepository(val api: BaseApi): BaseRepository() {

    suspend fun loginUser() = safeRemoteCall { api.loginUser() }
}