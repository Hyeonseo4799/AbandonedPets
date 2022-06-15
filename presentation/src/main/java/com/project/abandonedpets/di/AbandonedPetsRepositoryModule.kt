package com.project.abandonedpets.di

import com.project.data.repository.AbandonedPetsRepositoryImpl
import com.project.domain.repository.AbandonedPetsRepository
import org.koin.dsl.module

val abandonedPetsRepositoryModule = module {
    single<AbandonedPetsRepository> { AbandonedPetsRepositoryImpl(get()) }
}