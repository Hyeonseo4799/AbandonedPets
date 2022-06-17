package com.project.domain.model

data class AbandonedPets(
    val happenDate: String,
    val petImg : String,
    val petType: String,
    val petColor: String,
    val petAge: String,
    val petWeight: String,
    val noticeNo: String,
    val noticeStartDate: String,
    val noticeEndDate: String,
    val processState : String,
    var petGender: String,
    var neuterState: String,
    val specialFeature: String,
    val shelterName : String,
    val shelterTel: String,
    var shelterAddr: String,
    val organizeName: String
)