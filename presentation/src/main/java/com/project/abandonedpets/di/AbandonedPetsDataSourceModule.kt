package com.project.abandonedpets.di

import com.project.data.datasource.AbandonedPetsDataSource
import com.project.data.datasource.AbandonedPetsDataSourceImpl
import org.koin.dsl.module

val abandonedPetsDataSourceModule = module {
    single<AbandonedPetsDataSource> {
        AbandonedPetsDataSourceImpl(
            NetworkModule.provideAbandonedPetsApiService(NetworkModule.provideRetrofitInstance(
                    NetworkModule.provideConverterFactory())))
    }
}