package com.kagiya.pokedex.ui

import com.kagiya.pokedex.R

class BackgroundUtils {

    companion object{
        fun getBackgroundColor(pokemonType: String): String{

            val backgroundColor = when(pokemonType){
                "grass" -> "#EDF6EC"
                "fire" -> "#FCF3EB"
                "water" -> "#EBF1F8"
                "bug" -> "#F1F6E8"
                "electric" -> "#FBF8E9"
                "fairy" -> "#FBF1FA"
                "ground" -> "#F9EFEA"
                "rock" -> "#F7F5F1"
                "normal" -> "#F1F2F3"
                "poison" -> "#F5EDF8"
                "psychic" -> "#FCEEEF"
                "steel" -> "#ECF1F3"
                "dragon" -> "#E4EEF6"
                "fighting" -> "#F8E9EE"
                "dark" -> "#ECEBED"
                "ghost" -> "#EBEDF4"
                "ice" -> "#F1FBF9"
                "flying" -> "#F1F4FA"
                else -> "#EDF6EC"
            }

            return backgroundColor
        }

        fun getTypeBackgroundColor(pokemonType: String): String{

            val backgroundColor = when(pokemonType){
                "grass" -> "#63BC5A"
                "fire" -> "#FF9D55"
                "water" -> "#5090D6"
                "bug" -> "#91C12F"
                "electric" -> "#F4D23C"
                "fairy" -> "#EC8FE6"
                "ground" -> "#D97845"
                "rock" -> "#C5B78C"
                "normal" -> "#919AA2"
                "poison" -> "#B567CE"
                "psychic" -> "#FA7179"
                "steel" -> "#5A8EA2"
                "dragon" -> "#0B6DC3"
                "fighting" -> "#CE416B"
                "dark" -> "#5A5465"
                "ghost" -> "#5269AD"
                "ice" -> "#73CEC0"
                "flying" -> "#89AAE3"
                else -> "#EDF6EC"
            }

            return backgroundColor
        }

        fun getTypeImage(pokemonType: String) : Int{

            val typeImage = when(pokemonType){
                "grass" -> R.drawable.ic_grass_type
                "fire" -> R.drawable.ic_fire_type
                "water" -> R.drawable.ic_water_type
                "bug" -> R.drawable.ic_bug_type
                "electric" -> R.drawable.ic_electric_type
                "fairy" -> R.drawable.ic_fairy_type
                "ground" -> R.drawable.ic_ground_type
                "rock" -> R.drawable.ic_rock_type
                "normal" -> R.drawable.ic_normal_type
                "poison" -> R.drawable.ic_poison_type
                "psychic" -> R.drawable.ic_psychic_type
                "steel" -> R.drawable.ic_steel_type
                "dragon" -> R.drawable.ic_dragon_type
                "fighting" -> R.drawable.ic_fighting_type
                "dark" -> R.drawable.ic_dark_type
                "ghost" -> R.drawable.ic_ghost_type
                "ice" -> R.drawable.ic_ice_type
                "flying" -> R.drawable.ic_flying_type
                else -> throw IllegalArgumentException()
            }

            return typeImage
        }

        fun getOutlineImage(pokemonType: String) : Int{

            val typeImage = when(pokemonType){
                "grass" -> R.drawable.ic_grass_outline
                "fire" -> R.drawable.ic_fire_outline
                "water" -> R.drawable.ic_water_outline
                "bug" -> R.drawable.ic_bug_outline
                "electric" -> R.drawable.ic_electric_outline
                "fairy" -> R.drawable.ic_fairy_outline
                "ground" -> R.drawable.ic_ground_outline
                "rock" -> R.drawable.ic_rock_outline
                "normal" -> R.drawable.ic_normal_outline
                "poison" -> R.drawable.ic_poison_outline
                "psychic" -> R.drawable.ic_psychic_outline
                "steel" -> R.drawable.ic_steel_outline
                "dragon" -> R.drawable.ic_dragon_outline
                "fighting" -> R.drawable.ic_fighting_outline
                "dark" -> R.drawable.ic_dark_outline
                "ghost" -> R.drawable.ic_ghost_outline
                "ice" -> R.drawable.ic_ice_outline
                "flying" -> R.drawable.ic_flying_outline
                else -> throw IllegalArgumentException()
            }

            return typeImage
        }
    }
}