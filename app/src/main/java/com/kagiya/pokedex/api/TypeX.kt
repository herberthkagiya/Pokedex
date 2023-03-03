package com.kagiya.pokedex.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeX(
    val name: String,
    val url: String
)