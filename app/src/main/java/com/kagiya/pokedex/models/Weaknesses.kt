package com.kagiya.pokedex.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weaknesses(
    val damage_relations: DamageRelations
)