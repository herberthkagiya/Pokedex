package com.kagiya.pokedex.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.kagiya.pokedex.models.PokemonDetails
import com.kagiya.pokedex.databinding.ListItemPokedexOneTypeBinding
import com.kagiya.pokedex.databinding.ListItemPokedexTwoTypesBinding
import com.kagiya.pokedex.util.BackgroundUtils

private const val VIEW_ONE_POKEMON_TYPE = 0
private const val VIEW_TWO_POKEMON_TYPE = 1

class PokedexAdapter(
    private var pokemonList: List<PokemonDetails>,
    private val onPokemonClicked: (pokemonName: String) -> Unit
) : RecyclerView.Adapter<PokedexAdapter.PokemonHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            VIEW_ONE_POKEMON_TYPE -> {
                val binding = ListItemPokedexOneTypeBinding.inflate(inflater, parent, false)
                return PokemonOneTypeHolder(binding)
            }
            VIEW_TWO_POKEMON_TYPE -> {
                val binding = ListItemPokedexTwoTypesBinding.inflate(inflater, parent, false)
                return PokemonTwoTypesHolder(binding)
            }
            else -> throw java.lang.IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(pokemonList[position], onPokemonClicked)
    }

    override fun getItemViewType(position: Int): Int {
        return if(pokemonList[position].types.size > 1){
            VIEW_TWO_POKEMON_TYPE
        } else{
            VIEW_ONE_POKEMON_TYPE
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updateData(newPokemonList: List<PokemonDetails>) {
        pokemonList = newPokemonList
        notifyDataSetChanged()
    }

    abstract class PokemonHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(pokemon: PokemonDetails, onPokemonClicked: (pokemonName: String) -> Unit)
    }

    inner class PokemonOneTypeHolder(private val binding: ListItemPokedexOneTypeBinding) : PokemonHolder(binding) {

        override fun bind(pokemon: PokemonDetails, onPokemonClicked: (pokemonName: String) -> Unit) {

            binding.root.setOnClickListener{
                onPokemonClicked(pokemon.name)
            }

            binding.pokemonId.text = "N°" + pokemon.id.toString().padStart(3, '0')
            binding.pokemonName.text = pokemon.name.capitalize()

            setupBackgroundColorAndImagesInPokemonCard(pokemon)

            setupPokemonTypesInPokemonCard(pokemon)
        }

        private fun setupBackgroundColorAndImagesInPokemonCard(pokemon: PokemonDetails){

            val firstPokemonType = pokemon.types[0].type.name
            val backgroundProperties = BackgroundUtils.getBackgroundProperties(firstPokemonType)

            binding.root.background.setTint(Color.parseColor(backgroundProperties.cardBackgroundColor))
            binding.pokemonOutline.background.setTint(Color.parseColor(backgroundProperties.typeBackgroundColor))
            binding.pokemonOutline.setImageResource(backgroundProperties.outlineImage)
            binding.pokemonImage.load(pokemon.sprites.front_default)
        }

        private fun setupPokemonTypesInPokemonCard(pokemon: PokemonDetails){

            val pokemonType =  pokemon.types[0].type.name
            binding.typeName.text = pokemonType.capitalize()

            val backgroungProperties = BackgroundUtils.getBackgroundProperties(pokemonType)

            binding.type.background.setTint(Color.parseColor(backgroungProperties.typeBackgroundColor))

            binding.typeImage.setImageResource(backgroungProperties.typeImage)
        }
    }


    inner class PokemonTwoTypesHolder(private val binding: ListItemPokedexTwoTypesBinding) : PokemonHolder(binding) {
        override fun bind(pokemon: PokemonDetails, onPokemonClicked: (pokemonName: String) -> Unit) {

            binding.root.setOnClickListener{
                onPokemonClicked(pokemon.name)
            }

            binding.pokemonId.text = "N°" + pokemon.id.toString().padStart(3, '0')
            binding.pokemonName.text = pokemon.name.capitalize()

            setupBackgroundColorAndImagesInPokemonCard(pokemon)

            setupPokemonTypesInPokemonCard(pokemon)
        }

        private fun setupBackgroundColorAndImagesInPokemonCard(pokemon: PokemonDetails){

            val firstPokemonType = pokemon.types[0].type.name
            val backgroundProperties = BackgroundUtils.getBackgroundProperties(firstPokemonType)

            binding.root.background.setTint(Color.parseColor(backgroundProperties.cardBackgroundColor))
            binding.pokemonOutline.background.setTint(Color.parseColor(backgroundProperties.typeBackgroundColor))
            binding.pokemonOutline.setImageResource(backgroundProperties.outlineImage)
            binding.pokemonImage.load(pokemon.sprites.front_default)
        }

        private fun setupPokemonTypesInPokemonCard(pokemon: PokemonDetails){

            //Show first pokemon type
            val firstPokemonType =  pokemon.types[0].type.name
            binding.typeName1.text = firstPokemonType.capitalize()

            var backgroungProperties = BackgroundUtils.getBackgroundProperties(firstPokemonType)

            binding.type1.background.setTint(Color.parseColor(backgroungProperties.typeBackgroundColor))

            binding.typeImage1.setImageResource(backgroungProperties.typeImage)




            //Show first pokemon type
            val secondPokemonType =  pokemon.types[1].type.name
            binding.typeName2.text = secondPokemonType.capitalize()

            backgroungProperties = BackgroundUtils.getBackgroundProperties(secondPokemonType)

            binding.type2.background.setTint(Color.parseColor(backgroungProperties.typeBackgroundColor))

            binding.typeImage2.setImageResource(backgroungProperties.typeImage)
        }
    }


}