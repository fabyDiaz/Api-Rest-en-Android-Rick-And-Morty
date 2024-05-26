package com.example.rickandmorty.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.network.api.RickAndMortyApiService
import com.example.rickandmorty.data.network.retrofit.RetrofitHelper
import com.example.rickandmorty.data.repository.MainRepositoryImp
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.domain.PersonajeUseCase
import com.example.rickandmorty.presentation.adapter.PersonajeAdapter
import com.example.rickandmorty.presentation.viewmodel.PersonajeViewModel
import com.example.rickandmorty.presentation.viewmodel.PersonajeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PersonajeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var personajeAdapter: PersonajeAdapter
    private lateinit var binding: ActivityMainBinding

    val apiService = RetrofitHelper.getRetrofit().create(RickAndMortyApiService::class.java)
    val repository = MainRepositoryImp(apiService)
    val useCase = PersonajeUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = PersonajeViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PersonajeViewModel::class.java)

        recyclerView = binding.recyclerCharacters
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.allPersonaje.observe(this) { personajes ->
            Log.i("GET", personajes.toString())
            personajeAdapter = PersonajeAdapter(personajes)
            recyclerView.adapter = personajeAdapter
        }
    }
}