package com.hsdroid.aisletestapplication.network

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    var retrofit: Retrofit? = null
    private var apiRequests: APIInterface? = null

    fun getInstance(): APIInterface {
        if (apiRequests == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            apiRequests = getReqInstance().create(APIInterface::class.java)
            return apiRequests as APIInterface
        } else {
            return apiRequests as APIInterface
        }
    }


    fun getReqInstance(): Retrofit {

        if (retrofit == null) {
            return Retrofit.Builder()
                .baseUrl("https://testa2.aisle.co/V1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } else
            return retrofit as Retrofit
    }
}