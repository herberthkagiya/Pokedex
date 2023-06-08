package com.kagiya.pokedex.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.kagiya.pokedex.R
import com.kagiya.pokedex.data.PokemonCategory
import com.kagiya.pokedex.data.PokemonDescription
import com.kagiya.pokedex.data.PokemonDetails
import com.kagiya.pokedex.data.Weaknesses
import com.kagiya.pokedex.databinding.FragmentPokemonDetailsBinding
import com.kagiya.pokedex.viewmodel.PokemonDetailsViewModel
import com.kagiya.pokedex.viewmodel.PokemonDetailsViewModelFactory
import kotlinx.coroutines.launch

const val ONE_POKEMON_TYPE = 1
const val TWO_POKEMON_TYPES = 2

class PokemonDetailsFragment : Fragment(){

    private val args: PokemonDetailsFragmentArgs by navArgs()

    private lateinit var binding : FragmentPokemonDetailsBinding

    private val pokemonDetailsViewModel : PokemonDetailsViewModel by viewModels{
        PokemonDetailsViewModelFactory(args.pokemonName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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



        binding.weaknesses.layoutManager = GridLayoutManager(context, 2)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonDetailsViewModel.pokemonDetails.collect { pokemon ->
                   pokemon ?.let{
                       setPokemonDetails(it)
                   }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonDetailsViewModel.pokemonDescription.collect { description ->
                    description ?.let{
                        setupPokemonDescriptionInDetails(description)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonDetailsViewModel.pokemonCategory.collect { category ->
                    category ?.let{
                        setupPokemonCategoryInDetails(category)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonDetailsViewModel.pokemonWeaknesses.collect { weaknesses ->

                    weaknesses?.let{
                        setupPokemonWeaknesses(it)
                    }
                }
            }
        }
    }


    private fun setPokemonDetails(pokemon: PokemonDetails){

        binding.pokemonName.text = pokemon.name.capitalize()
        binding.pokemonId.text = "N°" + pokemon.id.toString().padStart(3, '0')


        setupBackgroundColorAndImagesInPokemonCard(pokemon)

        setupPokemonTypesInDetails(pokemon)


        binding.pokemonWeight.text = (pokemon.weight / 100.0).toString() + " Kg"
        binding.pokemonHeight.text = (pokemon.height / 10.0).toString() + " m"
        binding.pokemonAbility.text = pokemon.abilities[0].ability.name.capitalize()
    }

    private fun setupBackgroundColorAndImagesInPokemonCard(pokemon: PokemonDetails){

        //Change background color
        val firstPokemonType =  pokemon.types[0].type.name
        val backgroundProperties = BackgroundUtils.getBackgroundProperties(firstPokemonType)

        binding.pokemonBackground.setImageResource(R.drawable.bg_pokemon)
        binding.pokemonBackground.drawable.setTint(Color.parseColor(backgroundProperties.typeBackgroundColor))
        binding.pokemonOutline.setImageResource(backgroundProperties.outlineImage)
        binding.pokemonImage.load(pokemon.sprites.front_default)
    }

    private fun setupPokemonTypesInDetails(pokemon: PokemonDetails){

        if(pokemon.types.size == TWO_POKEMON_TYPES){
            setupTwoPokemonTypesInPokemonCard(pokemon)

        }
        else{
            setupOnePokemonTypeInPokemonCard(pokemon)

            binding.type2.removeAllViews()
        }
    }

    private fun setupTwoPokemonTypesInPokemonCard(pokemon: PokemonDetails){

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

    private fun setupOnePokemonTypeInPokemonCard(pokemon: PokemonDetails){

        val pokemonType =  pokemon.types[0].type.name
        binding.typeName1.text = pokemonType.capitalize()

        val backgroungProperties = BackgroundUtils.getBackgroundProperties(pokemonType)

        binding.type1.background.setTint(Color.parseColor(backgroungProperties.typeBackgroundColor))

        binding.typeImage1.setImageResource(backgroungProperties.typeImage)
    }


    private fun setupPokemonDescriptionInDetails(description: PokemonDescription){
        binding.pokemonDescription.text = description.flavor_text_entries[0].flavor_text.replace("\n", " ")
    }

    private fun setupPokemonCategoryInDetails(category: PokemonCategory){
        binding.pokemonCategory.text = category.shape.name.capitalize()
    }

    private fun setupPokemonWeaknesses(weaknesses: Weaknesses){
        binding.weaknesses.adapter = WeaknessesAdapter(weaknesses.damage_relations.double_damage_from)
    }
}