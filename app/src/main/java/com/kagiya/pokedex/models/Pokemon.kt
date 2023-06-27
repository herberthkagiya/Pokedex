package com.kagiya.pokedex.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Pokemon(
    val name: String,
    val url: String
)
