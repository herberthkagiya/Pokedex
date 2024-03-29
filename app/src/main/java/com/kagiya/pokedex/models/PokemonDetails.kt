package com.kagiya.pokedex.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PokemonDetails(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int,
)