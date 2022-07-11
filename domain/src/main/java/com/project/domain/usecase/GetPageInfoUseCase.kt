package com.project.domain.usecase

import com.project.domain.model.PageInfo
import com.project.domain.repository.AbandonedPetsRepository

class GetPageInfoUseCase(private val repository: AbandonedPetsRepository) {
    suspend operator fun invoke(bgnde: String, endde: String): PageInfo {
        return repository.getPageInfo(bgnde, endde)
    }
}