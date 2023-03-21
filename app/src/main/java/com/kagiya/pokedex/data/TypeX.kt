package com.kagiya.pokedex.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeX(
    val name: String,
    val url: String
)