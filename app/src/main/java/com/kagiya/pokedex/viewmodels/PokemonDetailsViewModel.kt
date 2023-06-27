package com.kagiya.pokedex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kagiya.pokedex.models.PokemonCategory
import com.kagiya.pokedex.models.PokemonDescription
import com.kagiya.pokedex.models.PokemonDetails
import com.kagiya.pokedex.data.PokemonRepository
import com.kagiya.pokedex.models.Weaknesses
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(pokemonName: String) : ViewModel() {


    private val pokemonRepository = PokemonRepository()

    private val _pokemonDetails: MutableStateFlow<PokemonDetails?> = MutableStateFlow(null)
    val pokemonDetails: StateFlow<PokemonDetails?> = _pokemonDetails.asStateFlow()


    private val _pokemonDescription: MutableStateFlow<PokemonDescription?> = MutableStateFlow(null)
    val pokemonDescription: StateFlow<PokemonDescription?> = _pokemonDescription.asStateFlow()

    private val _pokemonCategory: MutableStateFlow<PokemonCategory?> = MutableStateFlow(null)
    val pokemonCategory: StateFlow<PokemonCategory?> = _pokemonCategory.asStateFlow()

    private val _pokemonWeaknesses: MutableStateFlow<Weaknesses?> = MutableStateFlow(null)
    val pokemonWeaknesses: StateFlow<Weaknesses?> = _pokemonWeaknesses.asStateFlow()

    init{
        viewModelScope.launch {
            _pokemonDetails.value = pokemonRepository.searchPokemon(pokemonName)
            _pokemonDescription.value = pokemonRepository.getPokemonDescription(pokemonName)
            _pokemonCategory.value = pokemonRepository.getPokemonCategory(pokemonName)
            _pokemonWeaknesses.value = pokemonRepository.getPokemonWeaknesses(_pokemonDetails.value!!.types[0].type.name)
        }
    }
}

class PokemonDetailsViewModelFactory(
    private val pokemonName: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonDetailsViewModel(pokemonName) as T
    }
}