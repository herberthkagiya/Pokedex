package com.kagiya.pokedex.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonCategory(
    val shape: Shape
)