package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.Personaje

interface MainRespository {
    suspend fun getPersonajesAtService(): MutableList<Personaje>

}