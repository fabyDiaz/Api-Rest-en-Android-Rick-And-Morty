package com.example.rickandmorty.data.network.api

import com.example.rickandmorty.data.model.Personaje
import com.example.rickandmorty.data.model.RickAndMortyResponse
import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getAllPersonajes(): RickAndMortyResponse
}