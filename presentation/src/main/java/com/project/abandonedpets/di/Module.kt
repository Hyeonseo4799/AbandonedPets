package com.project.abandonedpets.di

import com.project.abandonedpets.di.NetworkModule.getAbandonedPetsApi
import com.project.abandonedpets.viewmodel.MainViewModel
import com.project.data.datasource.AbandonedPetsDataSource
import com.project.data.datasource.AbandonedPetsDataSourceImpl
import com.project.data.repository.AbandonedPetsRepositoryImpl
import com.project.domain.repository.AbandonedPetsRepository
import com.project.domain.usecase.GetPageInfoUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    viewModel { MainViewModel(get(), get()) }
    single { AbandonedPetsRepositoryImpl(get()) }
    single<AbandonedPetsRepository> { AbandonedPetsRepositoryImpl(get()) }
    single { GetPageInfoUseCase(get()) }
    single<AbandonedPetsDataSource> { AbandonedPetsDataSourceImpl(getAbandonedPetsApi()) }
}
