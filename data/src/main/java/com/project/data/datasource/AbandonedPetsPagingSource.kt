package com.project.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.data.repository.AbandonedPetsRepositoryImpl
import com.project.domain.model.AbandonedPets
import java.lang.Exception

class AbandonedPetsPagingSource(
    private val abandonedPetsRepositoryImpl: AbandonedPetsRepositoryImpl,
    private val beginDate: String,
    private val endDate: String
) : PagingSource<Int, AbandonedPets>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AbandonedPets> {
        val page = params.key ?: 1
        return try {
            val items = abandonedPetsRepositoryImpl.getInfo(beginDate, endDate, page.toString(), "50")
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + (params.loadSize / 10)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AbandonedPets>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}