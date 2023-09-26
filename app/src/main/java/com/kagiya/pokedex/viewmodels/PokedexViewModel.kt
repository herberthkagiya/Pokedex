package com.kagiya.pokedex.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagiya.pokedex.data.PokemonRepository
import com.kagiya.pokedex.models.Pokemon
import com.kagiya.pokedex.models.PokemonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "PokedexViewModel"
private const val QUERY_PAGE_SIZE = 10

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var pokemonPage = -10

    private val pokemons: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())
    private val _pokemonDetailedList: MutableStateFlow<List<PokemonDetails>> = MutableStateFlow(emptyList())

    val pokemonDetailedList: StateFlow<List<PokemonDetails>>
        get() = _pokemonDetailedList.asStateFlow()


    fun getPokemonList(){
        viewModelScope.launch {
            pokemonPage += 10

            val response = repository.fetchPokemonList(pokemonPage, 10)
            pokemons.value = response
            _pokemonDetailedList.value += repository.getPokemonDetails(pokemons.value)
        }
    }



    fun setQuery(query : String){
        viewModelScope.launch{
            try {
                _pokemonDetailedList.value = fetchPokemon(query)
            }
            catch (ex: Exception){
                Log.e(TAG, "Error at searching pokemon")
            }
        }
    }

    private suspend  fun fetchPokemon(query: String): List<PokemonDetails> {

        return if(query.isNotEmpty()){
            Log.d(TAG, "Query not empty")
            returnPokemonDetailInAList(repository.searchPokemon(query.lowercase()))
        }
        else{
            Log.d(TAG, "Query empty")
            repository.getPokemonDetails(pokemons.value)
        }
    }

    private fun returnPokemonDetailInAList(pokemon: PokemonDetails): List<PokemonDetails> {
        var list : List<PokemonDetails> = emptyList()
        list += pokemon
        return list
    }
}
