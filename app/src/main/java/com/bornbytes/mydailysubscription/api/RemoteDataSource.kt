package com.bornbytes.mydailysubscription.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object{
        const val BASE_URL = "https://www.github.com"
    }

    fun <Api> buildApi(clazz: Class<Api>): Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(clazz)
    }
}