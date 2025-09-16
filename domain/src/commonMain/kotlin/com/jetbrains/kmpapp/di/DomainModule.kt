package com.jetbrains.kmpapp.di


import com.jetbrains.kmpapp.di.useCases.GetCharacterPageUseCase
import com.jetbrains.kmpapp.di.useCases.GetCharacterUseCase
import com.jetbrains.kmpapp.di.useCases.GetContentUseCase
import org.koin.dsl.module

val domainModule = module {
    includes(dataModule)
    factory {
        GetCharacterPageUseCase(get())
    }
    factory {
        GetCharacterUseCase(get())
    }
    factory {
        GetContentUseCase(get())
    }
}