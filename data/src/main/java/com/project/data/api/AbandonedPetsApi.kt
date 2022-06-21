package com.project.data.api

import com.project.data.BuildConfig
import com.project.data.model.AbandonedPetsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AbandonedPetsApi {
    @GET("abandonmentPublic")
    suspend fun getInfo(
        @Query("bgnde") bgnde: String,
        @Query("endde") endde: String,
        @Query("pageNo") pageNo: String,
        @Query("numOfRows") numOfRows: String,
        @Query("_type") type: String = "json",
        @Query(value="serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY
    ): Response<AbandonedPetsResponse>
}