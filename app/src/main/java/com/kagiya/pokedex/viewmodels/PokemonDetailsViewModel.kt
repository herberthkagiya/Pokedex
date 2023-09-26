package com.kagiya.pokedex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kagiya.pokedex.models.PokemonCategory
import com.kagiya.pokedex.models.PokemonDescription
import com.kagiya.pokedex.models.PokemonDetails
import com.kagiya.pokedex.data.PokemonRepository
import com.kagiya.pokedex.models.Weaknesses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonDetails: MutableStateFlow<PokemonDetails?> = MutableStateFlow(null)
    val pokemonDetails: StateFlow<PokemonDetails?> = _pokemonDetails.asStateFlow()

    private val _pokemonDescription: MutableStateFlow<PokemonDescription?> = MutableStateFlow(null)
    val pokemonDescription: StateFlow<PokemonDescription?> = _pokemonDescription.asStateFlow()

    private val _pokemonCategory: MutableStateFlow<PokemonCategory?> = MutableStateFlow(null)
    val pokemonCategory: StateFlow<PokemonCategory?> = _pokemonCategory.asStateFlow()

    private val _pokemonWeaknesses: MutableStateFlow<Weaknesses?> = MutableStateFlow(null)
    val pokemonWeaknesses: StateFlow<Weaknesses?> = _pokemonWeaknesses.asStateFlow()


    fun loadPokemonDetails(pokemonName: String){
        viewModelScope.launch {
            _pokemonDetails.value = repository.searchPokemon(pokemonName)
            _pokemonDescription.value = repository.getPokemonDescription(pokemonName)
            _pokemonCategory.value = repository.getPokemonCategory(pokemonName)
            _pokemonWeaknesses.value = repository.getPokemonWeaknesses(_pokemonDetails.value!!.types[0].type.name)
        }
    }
}

//class PokemonDetailsViewModelFactory(
//    private val pokemonName: String
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return PokemonDetailsViewModel(pokemonName) as T
//    }
//}