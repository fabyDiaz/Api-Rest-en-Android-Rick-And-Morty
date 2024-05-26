package com.example.rickandmorty.data.network.api

import com.example.rickandmorty.data.model.Personaje
import com.example.rickandmorty.data.network.retrofit.RetrofitHelper

class RickAndMortyApiClient {

        private val helperRetrofit = RetrofitHelper.getRetrofit()
        suspend fun getAllPersonajes(): MutableList<Personaje> {
            return helperRetrofit.create(RickAndMortyApiService::class.java).getAllPersonajes().results
        }

}