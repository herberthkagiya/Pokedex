package com.kagiya.pokedex.di

import com.kagiya.pokedex.data.PokemonRepository
import com.kagiya.pokedex.data.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providePokemonService() : PokemonService{
        val baseUrl = "https://pokeapi.co/api/v2/"

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        val service: PokemonService = retrofit.create()
        return service
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        service: PokemonService
    ) : PokemonRepository{
        return PokemonRepository(service)
    }
}