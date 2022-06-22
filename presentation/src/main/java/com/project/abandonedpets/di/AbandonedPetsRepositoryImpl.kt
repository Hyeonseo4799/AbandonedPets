package com.project.abandonedpets.di

import com.project.data.repository.AbandonedPetsRepositoryImpl
import org.koin.dsl.module

val abandonedPetsRepositoryImpl = module {
    single  {
        AbandonedPetsRepositoryImpl(get())
    }
}