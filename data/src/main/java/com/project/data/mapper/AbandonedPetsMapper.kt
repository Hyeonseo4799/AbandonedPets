package com.project.data.mapper

import com.project.data.model.AbandonedPetsResponse
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo

object AbandonedPetsMapper {
    fun mapperToAbandonedPets(abandonedPetsResponse: AbandonedPetsResponse): List<AbandonedPets> {
        return abandonedPetsResponse.response.body.items.item.map {
            AbandonedPets(
                happenDate = it.happenDt,
                petImg = it.filename,
                petType = it.kindCd,
                petColor = it.colorCd,
                petAge = it.age,
                petWeight = it.weight,
                noticeNo = it.noticeNo,
                noticeStartDate = it.noticeSdt,
                noticeEndDate = it.noticeEdt,
                processState = it.processState,
                petGender = it.sexCd,
                neuterState = it.neuterYn,
                specialFeature = it.specialMark,
                shelterName = it.careNm,
                shelterTel = it.careTel,
                shelterAddr = it.careAddr,
            )
        }
    }

    fun mapperToPageInfo(abandonedPetsResponse: AbandonedPetsResponse): List<PageInfo> {
        return listOf(
            PageInfo(
                numOfRows = abandonedPetsResponse.response.body.numOfRows,
                pageNo = abandonedPetsResponse.response.body.pageNo,
                totalCount = abandonedPetsResponse.response.body.totalCount
            )
        )
    }
//    fun mapperToItem(abandonedPets: AbandonedPets): Item {
//        return Item(
//            happenDt = abandonedPets.happenDate,
//            filename = abandonedPets.petImg,
//            kindCd = abandonedPets.petType,
//            colorCd = abandonedPets.petColor,
//            age = abandonedPets.petAge,
//            weight = abandonedPets.petWeight,
//            noticeNo = abandonedPets.noticeNo,
//            noticeSdt = abandonedPets.noticeStartDate,
//            noticeEdt = abandonedPets.noticeEndDate,
//            processState = abandonedPets.processState,
//            sexCd = abandonedPets.petGender,
//            neuterYn = abandonedPets.neuterState,
//            specialMark = abandonedPets.specialFeature,
//            careNm = abandonedPets.shelterName,
//            careTel = abandonedPets.shelterTel,
//            careAddr = abandonedPets.shelterAddr
//        )
//    }
}