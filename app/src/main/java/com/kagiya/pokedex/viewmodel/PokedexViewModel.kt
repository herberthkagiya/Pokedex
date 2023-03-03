package com.kagiya.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagiya.pokedex.PokemonRepository
import com.kagiya.pokedex.api.Pokemon
import com.kagiya.pokedex.api.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


private const val TAG = "PokedexViewModel"

class PokedexViewModel : ViewModel() {

    private val _pokemons: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())
    val pokemons: StateFlow<List<Pokemon>>
        get() = _pokemons.asStateFlow()


    init {
        viewModelScope.launch {
            try {
                val response = PokemonRepository().fetchPokemonList()
                _pokemons.value = response
            }
            catch (ex: Exception) {
                Log.d(TAG, "Loading pokemons error", ex)
            }
        }
    }



    suspend fun getPokemonDetails(pokemons: List<Pokemon>) : List<PokemonDetails>{
        var pokemonDetails: List<PokemonDetails> = emptyList()

        pokemons.forEach() {
            val response = PokemonRepository().searchPokemon(it.name)
            pokemonDetails += response
        }

        return pokemonDetails
    }
}
