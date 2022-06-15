package com.project.abandonedpets.di

import com.project.domain.usecase.GetInfoUseCase
import org.koin.dsl.module

val getInfoUseCaseModule = module {
    single { GetInfoUseCase(get()) }
}