package com.example.rickandmorty.domain

import com.example.rickandmorty.data.model.Personaje
import com.example.rickandmorty.data.repository.MainRepositoryImp
import com.example.rickandmorty.data.repository.MainRespository

class PersonajeUseCase (private val repositoryImpl: MainRepositoryImp){


    suspend fun getAllPersonajes(): MutableList<Personaje> {
        return repositoryImpl.getPersonajesAtService()
    }

}