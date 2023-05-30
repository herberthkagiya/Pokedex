package com.kagiya.pokedex.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


class PokemonRepository {

    private val pokemonService: PokemonService

    init {
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        pokemonService = retrofit.create()
    }

    suspend fun fetchPokemonList(offset: Int, limit: Int): List<Pokemon> = pokemonService.fetchPokemonList(offset, limit).results


    suspend fun searchPokemon(name: String): PokemonDetails = pokemonService.searchPokemon(name)

    suspend fun getPokemonDetails(pokemons: List<Pokemon>) : List<PokemonDetails> {
        var details : List<PokemonDetails> = emptyList()

        pokemons.forEach() {
            val response = searchPokemon(it.name)
            details += response
        }

        return details
    }

    suspend fun getPokemonDescription(name: String) : PokemonDescription = pokemonService.getPokemonDescription(name)
}