package com.project.abandonedpets.di

import com.project.data.api.AbandonedPetsApi
import com.project.data.utils.util.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun getAbandonedPetsApi(): AbandonedPetsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Utils.BASE_URL)
            .build()
            .create(AbandonedPetsApi::class.java)
    }
}