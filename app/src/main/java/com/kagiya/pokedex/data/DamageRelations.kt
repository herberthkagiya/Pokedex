package com.kagiya.pokedex.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DamageRelations(
    val double_damage_from: List<DoubleDamageFrom>
)