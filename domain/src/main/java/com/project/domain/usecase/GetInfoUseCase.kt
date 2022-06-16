package com.project.domain.usecase

import com.project.domain.model.AbandonedPets
import com.project.domain.repository.AbandonedPetsRepository

class GetInfoUseCase(private val repository: AbandonedPetsRepository) {
    suspend operator fun invoke(bgnde: String, endde: String, pageNo: String, numOfRows: String): List<AbandonedPets> {
        return repository.getInfo(bgnde, endde, pageNo, numOfRows)
    }
}