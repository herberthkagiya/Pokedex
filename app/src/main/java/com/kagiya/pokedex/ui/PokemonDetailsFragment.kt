package com.kagiya.pokedex.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.kagiya.pokedex.R
import com.kagiya.pokedex.data.PokemonDetails
import com.kagiya.pokedex.databinding.FragmentPokemonDetailsBinding
import com.kagiya.pokedex.viewmodel.PokemonDetailsViewModel
import com.kagiya.pokedex.viewmodel.PokemonDetailsViewModelFactory
import kotlinx.coroutines.launch


class PokemonDetailsFragment : Fragment(){

    private val args: PokemonDetailsFragmentArgs by navArgs()

    private lateinit var binding : FragmentPokemonDetailsBinding

    private val pokemonDetailsViewModel : PokemonDetailsViewModel by viewModels{
        PokemonDetailsViewModelFactory(args.pokemonName)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonDetailsViewModel.pokemonDetails.collect { pokemon ->
                   pokemon ?.let{
                       setPokemonDetails(it)
                   }
                }
            }
        }
    }

    private fun setPokemonDetails(pokemon: PokemonDetails){
        binding.pokemonImage.load(pokemon.sprites.front_default)
        binding.pokemonName.text = pokemon.name.capitalize()
        binding.pokemonId.text = "N°" + pokemon.id.toString().padStart(3, '0')


        //Change background color
        val pokemonType1 =  pokemon.types[0].type.name
        val typeBackgroundColor1 = BackgroundUtils.getTypeBackgroundColor(pokemonType1)
        binding.pokemonBackground.setImageResource(R.drawable.bg_pokemon)
        binding.pokemonBackground.drawable.setTint(Color.parseColor(typeBackgroundColor1))


        //Change outline pokemon type
        binding.pokemonOutline.setImageResource(BackgroundUtils.getOutlineImage(pokemonType1))

        setPokemonTypesInDetails(pokemon)

        binding.pokemonWeight.text = pokemon.weight.toString() + " Kg"
        binding.pokemonHeight.text = pokemon.height.toString() + " m"
    }

    private fun setPokemonTypesInDetails(pokemon: PokemonDetails){

        if(pokemon.types.size == 2){
            //Show first pokemon types
            val pokemonType1 =  pokemon.types[0].type.name
            binding.typeName1.text = pokemonType1.capitalize()
            val typeBackgroundColor1 = BackgroundUtils.getTypeBackgroundColor(pokemonType1)
            binding.type1.background.setTint(Color.parseColor(typeBackgroundColor1))
            binding.type1.background.setTint(Color.parseColor(typeBackgroundColor1))
            val pokemonTypeImage1 = BackgroundUtils.getTypeImage(pokemonType1)
            binding.typeImage1.setImageResource(pokemonTypeImage1)


            //Show second pokemon type
            val pokemonType2 =  pokemon.types[1].type.name
            binding.typeName2.text = pokemonType2.capitalize()
            val typeBackgroundColor2 = BackgroundUtils.getTypeBackgroundColor(pokemonType2)
            binding.type2.background.setTint(Color.parseColor(typeBackgroundColor2))
            val pokemonTypeImage2 = BackgroundUtils.getTypeImage(pokemonType2)
            binding.typeImage2.setImageResource(pokemonTypeImage2)
        }
        else{
            //Show first pokemon types
            val pokemonType1 =  pokemon.types[0].type.name
            binding.typeName1.text = pokemonType1.capitalize()
            val typeBackgroundColor1 = BackgroundUtils.getTypeBackgroundColor(pokemonType1)
            binding.type1.background.setTint(Color.parseColor(typeBackgroundColor1))
            val pokemonTypeImage1 = BackgroundUtils.getTypeImage(pokemonType1)
            binding.typeImage1.setImageResource(pokemonTypeImage1)


            binding.type2.removeAllViews()
        }
    }
}