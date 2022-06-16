package com.project.data.datasource

import com.project.data.api.AbandonedPetsApi
import com.project.data.model.AbandonedPetsResponse
import com.project.data.utils.base.BaseRepository

class AbandonedPetsDataSourceImpl(private val api: AbandonedPetsApi) : BaseRepository(), AbandonedPetsDataSource {
    override suspend fun getInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): AbandonedPetsResponse {
        return safeApiCall { api.getInfo(bgnde, endde, pageNo, numOfRows).body() }!!
    }
}