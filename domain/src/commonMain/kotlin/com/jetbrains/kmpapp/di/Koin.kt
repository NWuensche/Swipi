package com.jetbrains.kmpapp.di


import com.jetbrains.kmpapp.di.useCases.GetCharacterPagesUseCase
import org.koin.dsl.module

val domainModule = module {
    includes(dataModule)
    factory {
        GetCharacterPagesUseCase(get())
    }
}