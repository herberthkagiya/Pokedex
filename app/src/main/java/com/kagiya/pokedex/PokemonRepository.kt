package com.kagiya.pokedex

import com.kagiya.pokedex.api.PokemonService
import com.kagiya.pokedex.api.Pokemon
import com.kagiya.pokedex.api.PokemonDetails
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


class PokemonRepository {

    private val pokemonService: PokemonService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        pokemonService = retrofit.create()
    }

    suspend fun fetchPokemonList(): List<Pokemon> = pokemonService.fetchPokemonList().results


    suspend fun searchPokemon(name: String): PokemonDetails = pokemonService.searchPokemon(name)
}