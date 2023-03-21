package com.kagiya.pokedex.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.kagiya.pokedex.ui.BackgroundUtils.Companion.getBackgroundColor
import com.kagiya.pokedex.ui.BackgroundUtils.Companion.getOutlineImage
import com.kagiya.pokedex.ui.BackgroundUtils.Companion.getTypeBackgroundColor
import com.kagiya.pokedex.ui.BackgroundUtils.Companion.getTypeImage
import com.kagiya.pokedex.data.PokemonDetails
import com.kagiya.pokedex.databinding.ListItemPokedexOneTypeBinding
import com.kagiya.pokedex.databinding.ListItemPokedexTwoTypesBinding


class PokedexAdapter(private val pokemonList: List<PokemonDetails>) : RecyclerView.Adapter<PokedexAdapter.PokemonHolder>() {

    private val VIEW_ONE_POKEMON_TYPE = 0
    private val VIEW_TWO_POKEMON_TYPE = 1

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
        when (holder){
            is PokemonOneTypeHolder -> holder.bind(pokemonList[position])
            is PokemonTwoTypesHolder -> holder.bind(pokemonList[position])
        }
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


    abstract class PokemonHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(pokemon: PokemonDetails)
    }

    inner class PokemonOneTypeHolder(private val binding: ListItemPokedexOneTypeBinding) :
        PokemonHolder(binding) {

        override fun bind(pokemon: PokemonDetails) {

            //Change pokedex item background color
            val rootBackgroundColor = getBackgroundColor(pokemon.types[0].type.name)
            binding.root.background.setTint(Color.parseColor(rootBackgroundColor))


            binding.pokemonId.text = "N°" + pokemon.id.toString().padStart(3, '0')
            binding.pokemonName.text = pokemon.name.capitalize()
            binding.pokemonImage.load(pokemon.sprites.front_default)

            //Show pokemon type
            val pokemonType =  pokemon.types[0].type.name
            binding.typeName.text = pokemonType.capitalize()
            val typeBackgroundColor = getTypeBackgroundColor(pokemonType)
            binding.type.background.setTint(Color.parseColor(typeBackgroundColor))
            val pokemonTypeImage = getTypeImage(pokemonType)
            binding.typeImage.setImageResource(pokemonTypeImage)

            //Change outline of pokemon image
            binding.pokemonOutline.background.setTint(Color.parseColor(typeBackgroundColor))
            binding.pokemonOutline.setImageResource(getOutlineImage(pokemonType))
        }
    }


    inner class PokemonTwoTypesHolder(private val binding: ListItemPokedexTwoTypesBinding) :
        PokemonHolder(binding) {
        override fun bind(pokemon: PokemonDetails) {
            //Change pokedex item background color
            val rootBackgroundColor = getBackgroundColor(pokemon.types[0].type.name)
            binding.root.background.setTint(Color.parseColor(rootBackgroundColor))

            binding.pokemonId.text = "N°" + pokemon.id.toString().padStart(3, '0')
            binding.pokemonName.text = pokemon.name.capitalize()
            binding.pokemonImage.load(pokemon.sprites.front_default)

            //Show first pokemon types
            val pokemonType1 =  pokemon.types[0].type.name
            binding.typeName1.text = pokemonType1.capitalize()
            val typeBackgroundColor1 = getTypeBackgroundColor(pokemonType1)
            binding.type1.background.setTint(Color.parseColor(typeBackgroundColor1))
            val pokemonTypeImage1 = getTypeImage(pokemonType1)
            binding.typeImage1.setImageResource(pokemonTypeImage1)


            //Show second pokemon type
            val pokemonType2 =  pokemon.types[1].type.name
            binding.typeName2.text = pokemonType2.capitalize()
            val typeBackgroundColor2 = getTypeBackgroundColor(pokemonType2)
            binding.type2.background.setTint(Color.parseColor(typeBackgroundColor2))
            val pokemonTypeImage2 = getTypeImage(pokemonType2)
            binding.typeImage2.setImageResource(pokemonTypeImage2)

            //Change outline of pokemon image
            binding.pokemonOutline.background.setTint(Color.parseColor(typeBackgroundColor1))
            binding.pokemonOutline.setImageResource(getOutlineImage(pokemonType1))
        }
    }
}