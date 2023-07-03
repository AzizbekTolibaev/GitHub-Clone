package com.example.githubclone

import android.app.Application
import com.example.githubclone.di.appModule
import com.example.githubclone.di.dataModule
import com.example.githubclone.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            modules(listOf(networkModule, dataModule, appModule))
            androidContext(this@App)
        }
    }

}