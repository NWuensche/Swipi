package com.jetbrains.kmpapp.http.responseTypes

import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterPageResponse(val results:List<CharacterResponse>)