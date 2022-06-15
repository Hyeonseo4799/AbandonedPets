package com.project.data.model

import com.google.gson.annotations.SerializedName

data class AbandonedPetsResponse(
    @SerializedName("happenDt") val happenDate: String,
    @SerializedName("popfile") val petImg : String,
    @SerializedName("kindCd") val petType: String,
    @SerializedName("colorCd") val petColor: String,
    @SerializedName("age") val petAge: Int,
    @SerializedName("weight") val petWeight: Int,
    @SerializedName("noticeNo") val noticeNo: String,
    @SerializedName("noticeSdt") val noticeStartDate: String,
    @SerializedName("noticeEdt") val noticeEndDate: String,
    @SerializedName("processState") val processState : String,
    @SerializedName("sexCd") val petGender: Char,
    @SerializedName("neuterYn") val neuterState: Char,
    @SerializedName("specialMark") val specialFeature: String,
    @SerializedName("careNm") val shelterName : String,
    @SerializedName("careTel") val shelterTel: String,
    @SerializedName("careAddr") val shelterAddr: String
)
