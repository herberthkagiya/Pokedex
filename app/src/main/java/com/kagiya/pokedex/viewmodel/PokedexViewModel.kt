package com.kagiya.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagiya.pokedex.data.PokemonRepository
import com.kagiya.pokedex.data.Pokemon
import com.kagiya.pokedex.data.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


private const val TAG = "PokedexViewModel"
private const val QUERY_PAGE_SIZE = 10

class PokedexViewModel : ViewModel() {

    var pokemonPage = -10


    private val pokemons: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())

    private val _pokemonDetails: MutableStateFlow<List<PokemonDetails>> = MutableStateFlow(emptyList())
    val pokemonDetails: StateFlow<List<PokemonDetails>>
        get() = _pokemonDetails.asStateFlow()


    init {
        viewModelScope.launch {
            try {
                getPokemonDetails()
            }
            catch (ex: Exception) {
                Log.d(TAG, "Loading pokemons error", ex)
            }
        }
    }

    fun getPokemonDetails(){
        viewModelScope.launch {
            pokemonPage += 10

            val response = PokemonRepository().fetchPokemonList(pokemonPage, 10)
            pokemons.value = response
            _pokemonDetails.value += PokemonRepository().getPokemonDetails(pokemons.value)
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
            PokemonRepository().getPokemonDetails(pokemons.value)
        }
    }

    private fun returnPokemonDetailInAList(pokemon: PokemonDetails): List<PokemonDetails> {
        var list : List<PokemonDetails> = emptyList()
        list += pokemon
        return list
    }
}
