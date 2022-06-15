package com.project.abandonedpets.di

import com.project.data.api.AbandonedPetsApi
import com.project.data.utils.util.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun provideRetrofitInstance(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(Utils.BASE_URL)
            .build()
    }

    fun provideAbandonedPetsApiService(retrofit: Retrofit): AbandonedPetsApi {
        return retrofit.create(AbandonedPetsApi::class.java)
    }

    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}