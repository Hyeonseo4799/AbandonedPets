package com.project.domain.repository

import androidx.paging.PagingData
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo
import kotlinx.coroutines.flow.Flow

interface AbandonedPetsRepository {
    suspend fun getInfo(bgnde: String, endde: String, page: String, numOfRows: String) : List<AbandonedPets>
    suspend fun getPageInfo(bgnde: String, endde: String): PageInfo
    suspend fun getInfoByPaging(bgnde: String, endde: String): Flow<PagingData<AbandonedPets>>
}