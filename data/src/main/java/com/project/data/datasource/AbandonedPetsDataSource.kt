package com.project.data.datasource

import com.project.data.model.AbandonedPetsResponse

interface AbandonedPetsDataSource {
    suspend fun getInfo(bgnde: String, endde: String, page: String = "1", numOfRows: String = "10"): AbandonedPetsResponse
}