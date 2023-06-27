package com.kagiya.pokedex.data

import com.kagiya.pokedex.models.PokeApiResponse
import com.kagiya.pokedex.models.PokemonCategory
import com.kagiya.pokedex.models.PokemonDescription
import com.kagiya.pokedex.models.PokemonDetails
import com.kagiya.pokedex.models.Weaknesses
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

    @GET("https://pokeapi.co/api/v2/pokemon-species/{name}")
    suspend fun getPokemonCategory(@Path("name") name: String) : PokemonCategory

    @GET("https://pokeapi.co/api/v2/type/{typeName}")
    suspend fun getPokemonWeaknesses(@Path("typeName") typename: String) : Weaknesses
}