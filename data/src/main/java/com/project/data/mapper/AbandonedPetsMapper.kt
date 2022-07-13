package com.project.data.mapper

import com.project.data.model.AbandonedPetsResponse
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo

object AbandonedPetsMapper {
    fun mapperToAbandonedPets(abandonedPetsResponse: AbandonedPetsResponse): List<AbandonedPets> {
        return abandonedPetsResponse.response.body.items.item.map {
            AbandonedPets(
                happenDate = it.happenDt,
                thumbnail = it.filename,
                image = it.popfile,
                happenPlace = it.happenPlace,
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
                organizeName = it.orgNm
            )
        }
    }

    fun mapperToPageInfo(abandonedPetsResponse: AbandonedPetsResponse): PageInfo {
        return PageInfo(
            numOfRows = abandonedPetsResponse.response.body.numOfRows,
            pageNo = abandonedPetsResponse.response.body.pageNo,
            totalCount = abandonedPetsResponse.response.body.totalCount
        )
    }
}