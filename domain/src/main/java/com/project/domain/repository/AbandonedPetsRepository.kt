package com.project.domain.repository

import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo

interface AbandonedPetsRepository {
    suspend fun getInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): List<AbandonedPets>
    suspend fun getPageInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): PageInfo
}