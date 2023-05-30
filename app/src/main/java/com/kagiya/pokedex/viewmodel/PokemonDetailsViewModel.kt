package com.kagiya.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kagiya.pokedex.data.PokemonDescription
import com.kagiya.pokedex.data.PokemonDetails
import com.kagiya.pokedex.data.PokemonRepository
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

    init{
        viewModelScope.launch {
            _pokemonDetails.value = pokemonRepository.searchPokemon(pokemonName)
            _pokemonDescription.value = pokemonRepository.getPokemonDescription(pokemonName)
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