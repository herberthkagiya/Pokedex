package com.kagiya.pokedex.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    val flavor_text: String,
    val version: Version
)