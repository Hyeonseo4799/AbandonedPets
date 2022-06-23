package com.project.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.data.datasource.AbandonedPetsDataSource
import com.project.data.datasource.AbandonedPetsPagingSource
import com.project.data.mapper.AbandonedPetsMapper
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo
import com.project.domain.repository.AbandonedPetsRepository
import kotlinx.coroutines.flow.Flow

class AbandonedPetsRepositoryImpl(private val dataSource: AbandonedPetsDataSource) : AbandonedPetsRepository {
    override suspend fun getInfo(bgnde: String, endde: String, page: String, numOfRows: String): List<AbandonedPets> {
        return AbandonedPetsMapper.mapperToAbandonedPets(dataSource.getInfo(bgnde, endde, page, numOfRows))
    }

    override suspend fun getPageInfo(bgnde: String, endde: String): PageInfo {
        return AbandonedPetsMapper.mapperToPageInfo(dataSource.getInfo(bgnde, endde))
    }

    override suspend fun getInfoByPaging(bgnde: String, endde: String): Flow<PagingData<AbandonedPets>> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            pagingSourceFactory = { AbandonedPetsPagingSource(this, bgnde, endde) }
        ).flow
    }
}
