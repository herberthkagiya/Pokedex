package com.kagiya.pokedex.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDescription(
    val flavor_text_entries: List<FlavorTextEntry>
)