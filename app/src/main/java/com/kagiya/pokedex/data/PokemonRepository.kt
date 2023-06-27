package com.kagiya.pokedex.data

import com.kagiya.pokedex.models.Pokemon
import com.kagiya.pokedex.models.PokemonCategory
import com.kagiya.pokedex.models.PokemonDescription
import com.kagiya.pokedex.models.PokemonDetails
import com.kagiya.pokedex.models.Weaknesses
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://pokeapi.co/api/v2/"


class PokemonRepository {

    private val pokemonService: PokemonService

    init {
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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

    suspend fun getPokemonCategory(name: String) : PokemonCategory = pokemonService.getPokemonCategory(name)

    suspend fun getPokemonWeaknesses(typeName: String) : Weaknesses = pokemonService.getPokemonWeaknesses(typeName)
}