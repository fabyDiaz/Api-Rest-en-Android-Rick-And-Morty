package com.example.rickandmorty.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.Personaje
import com.example.rickandmorty.domain.PersonajeUseCase
import kotlinx.coroutines.launch

class PersonajeViewModel(private val useCase: PersonajeUseCase): ViewModel() {
    private val _allPersonaje = MutableLiveData<MutableList<Personaje>>()
    val allPersonaje: LiveData<MutableList<Personaje>>
        get() = _allPersonaje

    init {
        getAllPersonajes()
    }

    fun getAllPersonajes() {
        viewModelScope.launch {
            _allPersonaje.value = useCase.getAllPersonajes()
        }
    }
}