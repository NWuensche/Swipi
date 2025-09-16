package com.jetbrains.kmpapp.states

import com.jetbrains.kmpapp.di.entities.Content

sealed interface BottomSheetState {
    object Loading : BottomSheetState
    object Error : BottomSheetState
    data class ContentView(
        val content: Content
    ) : BottomSheetState
}