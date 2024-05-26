package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.Personaje
import com.example.rickandmorty.data.network.api.RickAndMortyApiService

class MainRepositoryImp(private val apiService: RickAndMortyApiService) : MainRespository{

    override suspend fun getPersonajesAtService(): MutableList<Personaje> {
        return apiService.getAllPersonajes().results
    }
}
