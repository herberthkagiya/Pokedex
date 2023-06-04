package com.kagiya.pokedex.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weaknesses(
    val damage_relations: DamageRelations
)