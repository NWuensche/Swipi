package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.DetailViewModel
import featureCharacterDetailModule
import featureCharacterListModule
import org.koin.dsl.module

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            listOf(
                featureCharacterListModule, //TODO Lazy
                featureCharacterDetailModule,
                module {
                    factory { DetailViewModel(get()) }
                }
            )
        )
    }
}
