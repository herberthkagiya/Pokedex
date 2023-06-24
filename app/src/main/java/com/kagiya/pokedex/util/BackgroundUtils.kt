package com.kagiya.pokedex.util

import com.kagiya.pokedex.R

class BackgroundUtils {

    companion object {
        fun getBackgroundProperties(pokemonType: String): PokemonBackground {

            val background = when (pokemonType) {

                "grass" -> PokemonBackground(
                    "#EDF6EC",
                    "#63BC5A",
                    R.drawable.ic_grass_type,
                    R.drawable.ic_grass_outline
                )

                "fire" -> PokemonBackground(
                    "#FCF3EB",
                    "#FF9D55",
                    R.drawable.ic_fire_type,
                    R.drawable.ic_fire_outline
                )

                "water" -> PokemonBackground(
                    "#EBF1F8",
                    "#5090D6",
                    R.drawable.ic_water_type,
                    R.drawable.ic_water_outline
                )

                "bug" -> PokemonBackground(
                    "#F1F6E8",
                    "#91C12F",
                    R.drawable.ic_bug_type,
                    R.drawable.ic_bug_outline
                )

                "electric" -> PokemonBackground(
                    "#FBF8E9",
                    "#F4D23C",
                    R.drawable.ic_electric_type,
                    R.drawable.ic_electric_outline
                )

                "fairy" -> PokemonBackground(
                    "#FBF1FA",
                    "#EC8FE6",
                    R.drawable.ic_fairy_type,
                    R.drawable.ic_fairy_outline
                )

                "ground" -> PokemonBackground(
                    "#F9EFEA",
                    "#D97845",
                    R.drawable.ic_ground_type,
                    R.drawable.ic_ground_outline
                )

                "rock" -> PokemonBackground(
                    "#F7F5F1",
                    "#C5B78C",
                    R.drawable.ic_rock_type,
                    R.drawable.ic_rock_outline
                )

                "normal" -> PokemonBackground(
                    "#F1F2F3",
                    "#919AA2",
                    R.drawable.ic_normal_type,
                    R.drawable.ic_normal_outline
                )

                "poison" -> PokemonBackground(
                    "#F5EDF8",
                    "#B567CE",
                    R.drawable.ic_poison_type,
                    R.drawable.ic_poison_outline
                )

                "psychic" -> PokemonBackground(
                    "#FCEEEF",
                    "#FA7179",
                    R.drawable.ic_psychic_type,
                    R.drawable.ic_psychic_outline
                )

                "steel" -> PokemonBackground(
                    "#ECF1F3",
                    "#5A8EA2",
                    R.drawable.ic_steel_type,
                    R.drawable.ic_steel_outline
                )

                "dragon" -> PokemonBackground(
                    "#E4EEF6",
                    "#0B6DC3",
                    R.drawable.ic_dragon_type,
                    R.drawable.ic_dragon_outline
                )

                "fighting" -> PokemonBackground(
                    "#F8E9EE",
                    "#CE416B",
                    R.drawable.ic_fighting_type,
                    R.drawable.ic_fighting_outline
                )

                "dark" -> PokemonBackground(
                    "#ECEBED",
                    "#5A5465",
                    R.drawable.ic_dark_type,
                    R.drawable.ic_dark_outline
                )

                "ghost" -> PokemonBackground(
                    "#EBEDF4",
                    "#5269AD",
                    R.drawable.ic_ghost_type,
                    R.drawable.ic_ghost_outline
                )

                "ice" -> PokemonBackground(
                    "#F1FBF9",
                    "#73CEC0",
                    R.drawable.ic_ice_type,
                    R.drawable.ic_ice_outline
                )

                "flying" -> PokemonBackground(
                    "#F1F4FA",
                    "#89AAE3",
                    R.drawable.ic_flying_type,
                    R.drawable.ic_flying_outline
                )

                else -> throw IllegalArgumentException()
            }

            return background
        }
    }
}
class PokemonBackground(
    val cardBackgroundColor: String,
    val typeBackgroundColor: String,
    val typeImage: Int,
    val outlineImage: Int)

