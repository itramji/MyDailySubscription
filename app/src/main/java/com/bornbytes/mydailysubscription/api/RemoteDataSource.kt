package com.bornbytes.mydailysubscription.api

import com.bornbytes.mydailysubscription.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        const val BASE_URL = "https://www.github.com"
    }

    fun buildApi(clazz: Class<BaseApi> = BaseApi::class.java, token: String? = null): BaseApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().apply {
                    token?.let {
                        addInterceptor { chain ->
                            chain.proceed(chain.request().newBuilder().also {
                                it.addHeader("Authorization", "Bearer $token")
                            }.build())
                        }
                    }
                }.also { client ->
                    if (BuildConfig.DEBUG) {
                        client.addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(clazz)
    }
}