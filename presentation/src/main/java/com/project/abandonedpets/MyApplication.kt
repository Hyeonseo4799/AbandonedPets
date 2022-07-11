package com.project.abandonedpets

import android.app.Application
import com.project.abandonedpets.di.*
import org.koin.core.context.startKoin

class MyApplication : Application() {
    private lateinit var dataStore: DataStoreRadioValue

    companion object {
        private lateinit var myApplication: MyApplication
        fun getInstance(): MyApplication = myApplication
    }

    override fun onCreate() {
        super.onCreate()
        myApplication = this@MyApplication
        startKoin { modules(applicationModule) }
        dataStore = DataStoreRadioValue(this@MyApplication)
    }

    fun getDataStore(): DataStoreRadioValue = dataStore
}