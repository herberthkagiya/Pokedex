package com.kagiya.pokedex.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeApiResponse(
    val results: List<Pokemon>
)
