package com.project.abandonedpets.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.GetInfoUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val getInfoUseCase: GetInfoUseCase) : ViewModel() {
    fun getInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String) {
        viewModelScope.launch {
            val list = getInfoUseCase(bgnde, endde, pageNo, numOfRows)
            Log.d("list", list.toString())
        }
    }
}