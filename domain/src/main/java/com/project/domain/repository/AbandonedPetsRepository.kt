package com.project.domain.repository

import com.project.domain.model.AbandonedPets

interface AbandonedPetsRepository {
    suspend fun getInfo(bgnde: String, endde: String, pageNo: String, numOfRows: String): AbandonedPets?
}