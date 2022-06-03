package com.project.domain.model

data class AbandonedPets(
    val happenDate: String,
    val petImg : String,
    val petType: String,
    val petColor: String,
    val petAge: Int,
    val petWeight: Int,
    val noticeNo: String,
    val noticeStartDate: String,
    val noticeEndDate: String,
    val processState : String,
    val petGender: Char,
    val neuterState: Char,
    val specialFeature: String,
    val shelterName : String,
    val shelterTel: String,
    val shelterAddr: String
)