package com.kagiya.pokedex.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kagiya.pokedex.api.PokemonDetails
import com.kagiya.pokedex.databinding.ListItemPokedexBinding


class PokemonViewHolder(
    val binding: ListItemPokedexBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(pokemon: PokemonDetails){
        binding.pokemonId.text = "N°" + pokemon.id.toString().padStart(3, '0')
        binding.pokemonName.text = pokemon.name.capitalize()
        binding.pokemonImage.load(pokemon.sprites.front_default)

        val backgroundColor = getBackgroundColor(pokemon.types[0].type.name)

        binding.root.background.setTint(Color.parseColor(backgroundColor))
    }

    private fun getBackgroundColor(pokemonType: String): String{

        val backgroundColor = when(pokemonType){
            "grass" -> "#EDF6EC"
            "fire" -> "#FCF3EB"
            "water" -> "#EBF1F8"
            "insect" -> "#F1F6E8"
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
}


class PokedexAdapter(val pokemonList: List<PokemonDetails>) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPokedexBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}