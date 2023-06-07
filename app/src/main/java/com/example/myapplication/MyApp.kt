package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.apiModule
import com.example.myapplication.di.repoModule
import com.example.myapplication.di.roomDataModelModule
import com.example.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(apiModule, viewModelModule, repoModule , roomDataModelModule))
            // set the property at rune time
            //setProperty("base_url", "https://heptagon.tech/")
        }
    }
}