package com.example.letscube.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    fun getRetrofitBuilder() : Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("https://www.worldcubeassociation.org/")
            .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }
}