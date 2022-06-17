package com.project.data.repository

import com.project.data.datasource.AbandonedPetsDataSource
import com.project.data.mapper.AbandonedPetsMapper
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo
import com.project.domain.repository.AbandonedPetsRepository

class AbandonedPetsRepositoryImpl(private val dataSource: AbandonedPetsDataSource) : AbandonedPetsRepository {
    override suspend fun getInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): List<AbandonedPets> {
        return AbandonedPetsMapper.mapperToAbandonedPets(dataSource.getInfo(bgnde, endde, pageNo, numOfRows))
    }

    override suspend fun getPageInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): PageInfo {
        return AbandonedPetsMapper.mapperToPageInfo(dataSource.getInfo(bgnde, endde, pageNo, numOfRows))
    }
}
