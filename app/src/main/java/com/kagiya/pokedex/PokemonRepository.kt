package com.kagiya.pokedex

import com.kagiya.pokedex.api.Pokemon
import com.kagiya.pokedex.api.PokemonDetails
import com.kagiya.pokedex.api.PokemonService
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
}