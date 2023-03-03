package com.kagiya.pokedex.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kagiya.pokedex.PokemonRepository
import com.kagiya.pokedex.api.Pokemon
import com.kagiya.pokedex.api.PokemonDetails
import com.kagiya.pokedex.databinding.ItemPokedexListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewHolder(
    val binding: ItemPokedexListBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(pokemon: PokemonDetails){

        binding.pokemonName.text = pokemon.name

        binding.pokemonImage.load(pokemon.sprites.front_default)
    }
}


class PokedexAdapter(val pokemonList: List<PokemonDetails>) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokedexListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}