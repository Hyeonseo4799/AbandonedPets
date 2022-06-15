package com.project.abandonedpets

import android.app.Application
import com.project.abandonedpets.di.*
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(abandonedPetsDataSourceModule, abandonedPetsRepositoryModule, getInfoUseCaseModule, viewModelModule)
        }
    }
}