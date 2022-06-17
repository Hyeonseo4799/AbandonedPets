package com.project.abandonedpets.di

import com.project.domain.usecase.GetPageInfoUseCase
import org.koin.dsl.module

val getPageInfoUseCase = module {
    single { GetPageInfoUseCase(get()) }
}