package com.example.rickandmorty.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.rickandmorty.domain.PersonajeUseCase

class PersonajeViewModelFactory(private val useCase:PersonajeUseCase ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonajeViewModel::class.java)) {
            return PersonajeViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}