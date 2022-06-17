package com.project.abandonedpets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo
import com.project.domain.usecase.GetInfoUseCase
import com.project.domain.usecase.GetPageInfoUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val getInfoUseCase: GetInfoUseCase, private val getPageInfoUseCase: GetPageInfoUseCase) : ViewModel() {
    fun getInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): LiveData<List<AbandonedPets>> {
        val abandonedPetsList = MutableLiveData<List<AbandonedPets>>()
        viewModelScope.launch {
            abandonedPetsList.value = getInfoUseCase(bgnde, endde, pageNo, numOfRows)
        }
        return abandonedPetsList
    }

    fun getPageInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): LiveData<PageInfo> {
        val pageInfoList = MutableLiveData<PageInfo>()
        viewModelScope.launch {
            pageInfoList.value = getPageInfoUseCase(bgnde, endde, pageNo, numOfRows)
        }
        return pageInfoList
    }


}