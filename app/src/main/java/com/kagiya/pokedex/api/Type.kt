package com.kagiya.pokedex.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Type(
    val slot: Int,
    val type: TypeX
)