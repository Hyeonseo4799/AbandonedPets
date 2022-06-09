package com.project.data.api

import com.project.data.BuildConfig
import com.project.data.model.AbandonedPetsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AbandonedPetsApi {
    @GET("abandonmentPublic")
    fun getInfo(
        @Query("bgnde") bgnde: String,
        @Query("endde") endde: String,
        @Query("pageNo") pageNo: String,
        @Query("numOfRows") numOfRows: String,
        @Query("_type") type: String = "json",
        @Query("serviceKey") serviceKey: String = BuildConfig.API_KEY
    ): Call<AbandonedPetsResponse>
}