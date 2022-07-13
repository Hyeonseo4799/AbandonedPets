package com.project.domain.model

import java.io.Serializable

data class AbandonedPets(
    val happenDate: String,
    val thumbnail : String,
    val image : String,
    val happenPlace: String,
    val petType: String,
    val petColor: String,
    var petAge: String,
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
) : Serializable