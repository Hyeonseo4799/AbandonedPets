package com.project.abandonedpets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.project.data.repository.AbandonedPetsRepositoryImpl
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo
import com.project.domain.usecase.GetPageInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getPageInfoUseCase: GetPageInfoUseCase, private val abandonedPetsRepositoryImpl: AbandonedPetsRepositoryImpl) :
    ViewModel() {
    fun getPageInfo(bgnde: String, endde: String): LiveData<PageInfo> {
        val pageInfoList = MutableLiveData<PageInfo>()
        viewModelScope.launch {
            pageInfoList.value = getPageInfoUseCase(bgnde, endde)
        }
        return pageInfoList
    }

    suspend fun getInfo(beginDate: String, endDate: String): Flow<PagingData<AbandonedPets>> {
        val pagingData = withContext(Dispatchers.IO) {
            abandonedPetsRepositoryImpl.getInfoByPaging(beginDate, endDate)
        }
        return pagingData
    }
}