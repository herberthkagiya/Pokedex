package com.kagiya.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagiya.pokedex.PokemonRepository
import com.kagiya.pokedex.data.Pokemon
import com.kagiya.pokedex.data.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


private const val TAG = "PokedexViewModel"

class PokedexViewModel : ViewModel() {

    private val _pokemons: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())
    val pokemons: StateFlow<List<Pokemon>>
        get() = _pokemons.asStateFlow()


    private val _pokemonDetails: MutableStateFlow<List<PokemonDetails>> = MutableStateFlow(emptyList())
    val pokemonDetails: StateFlow<List<PokemonDetails>>
        get() = _pokemonDetails.asStateFlow()


    init {
        viewModelScope.launch {
            try {
                val response = PokemonRepository().fetchPokemonList(0, 10)
                _pokemons.value = response
                _pokemonDetails.value = PokemonRepository().getPokemonDetails(_pokemons.value)
            }
            catch (ex: Exception) {
                Log.d(TAG, "Loading pokemons error", ex)
            }
        }
    }


    fun setQuery(query : String){
        viewModelScope.launch{
            try {
                _pokemonDetails.value = fetchPokemon(query)
            }
            catch (ex: Exception){
                Log.e(TAG, "Error at searching pokemon")
            }
        }
    }

    private suspend  fun fetchPokemon(query: String): List<PokemonDetails> {

        return if(query.isNotEmpty()){
            Log.d(TAG, "Query not empty")
            returnPokemonDetailInAList(PokemonRepository().searchPokemon(query.lowercase()))
        }
        else{
            Log.d(TAG, "Query empty")
            PokemonRepository().getPokemonDetails(_pokemons.value)
        }
    }

    private fun returnPokemonDetailInAList(pokemon: PokemonDetails): List<PokemonDetails> {
        var list : List<PokemonDetails> = emptyList()
        list += pokemon
        return list
    }
}
