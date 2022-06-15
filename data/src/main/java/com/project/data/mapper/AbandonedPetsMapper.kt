package com.project.data.mapper

import com.project.data.model.AbandonedPetsResponse
import com.project.domain.model.AbandonedPets

object AbandonedPetsMapper {
    fun mapperToAbandonedPets(abandonedPetsResponse: AbandonedPetsResponse?): AbandonedPets? {
        if (abandonedPetsResponse != null) {
            return AbandonedPets(
                happenDate = abandonedPetsResponse.happenDate,
                petImg = abandonedPetsResponse.petImg,
                petType = abandonedPetsResponse.petType,
                petColor = abandonedPetsResponse.petColor,
                petAge = abandonedPetsResponse.petAge,
                petWeight = abandonedPetsResponse.petWeight,
                noticeNo = abandonedPetsResponse.noticeNo,
                noticeStartDate = abandonedPetsResponse.noticeStartDate,
                noticeEndDate = abandonedPetsResponse.noticeEndDate,
                processState = abandonedPetsResponse.processState,
                petGender = abandonedPetsResponse.petGender,
                neuterState = abandonedPetsResponse.neuterState,
                specialFeature = abandonedPetsResponse.specialFeature,
                shelterName = abandonedPetsResponse.shelterName,
                shelterTel = abandonedPetsResponse.shelterTel,
                shelterAddr = abandonedPetsResponse.shelterAddr
            )
        } else
            return null
    }

    fun mapperToAbandonedPetsResponse(abandonedPets: AbandonedPets): AbandonedPetsResponse {
        return AbandonedPetsResponse(
            happenDate = abandonedPets.happenDate,
            petImg = abandonedPets.petImg,
            petType = abandonedPets.petType,
            petColor = abandonedPets.petColor,
            petAge = abandonedPets.petAge,
            petWeight = abandonedPets.petWeight,
            noticeNo = abandonedPets.noticeNo,
            noticeStartDate = abandonedPets.noticeStartDate,
            noticeEndDate = abandonedPets.noticeEndDate,
            processState = abandonedPets.processState,
            petGender = abandonedPets.petGender,
            neuterState = abandonedPets.neuterState,
            specialFeature = abandonedPets.specialFeature,
            shelterName = abandonedPets.shelterName,
            shelterTel = abandonedPets.shelterTel,
            shelterAddr = abandonedPets.shelterAddr
        )
    }
}