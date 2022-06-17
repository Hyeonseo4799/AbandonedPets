package com.project.data.datasource

import com.project.data.model.AbandonedPetsResponse

interface AbandonedPetsDataSource {
    suspend fun getInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): AbandonedPetsResponse
    suspend fun getPageInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): AbandonedPetsResponse
}