package com.jetbrains.kmpapp

import android.app.Application
import featureCharacterDetailModule
import featureCharacterListModule
import org.koin.core.context.startKoin

class SwipiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                featureCharacterListModule,
                featureCharacterDetailModule,
            )
        }
    }
}
