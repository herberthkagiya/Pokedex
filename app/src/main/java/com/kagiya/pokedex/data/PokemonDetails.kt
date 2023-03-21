package com.kagiya.pokedex.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PokemonDetails(
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
)