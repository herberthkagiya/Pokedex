package com.kagiya.pokedex.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Sprites(
    val front_default: String
)