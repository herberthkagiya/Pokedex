package com.kagiya.pokedex.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonService {

    @GET("pokemon")
    suspend fun fetchPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int ) : PokeApiResponse

    @GET("https://pokeapi.co/api/v2/pokemon/{name}/")
    suspend fun searchPokemon(@Path("name") name: String) : PokemonDetails

    @GET("https://pokeapi.co/api/v2/pokemon-species/{name}")
    suspend fun getPokemonDescription(@Path("name") name: String) : PokemonDescription
}