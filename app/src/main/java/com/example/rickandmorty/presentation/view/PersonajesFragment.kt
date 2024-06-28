package com.example.rickandmorty.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.network.api.RickAndMortyApiService
import com.example.rickandmorty.data.network.retrofit.RetrofitHelper
import com.example.rickandmorty.data.repository.MainRepositoryImp
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.databinding.FragmentPersonajesBinding
import com.example.rickandmorty.domain.PersonajeUseCase
import com.example.rickandmorty.presentation.adapter.PersonajeAdapter
import com.example.rickandmorty.presentation.viewmodel.PersonajeViewModel
import com.example.rickandmorty.presentation.viewmodel.PersonajeViewModelFactory


class PersonajesFragment : Fragment() {

    private var _binding: FragmentPersonajesBinding? = null
    private val binding get() = _binding!!

    private lateinit var personajesViewModel: PersonajeViewModel
    private lateinit var personajeAdapter: PersonajeAdapter

    val apiService = RetrofitHelper.getRetrofit().create(RickAndMortyApiService::class.java)
    val repository = MainRepositoryImp(apiService)
    val useCase = PersonajeUseCase(repository)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        val viewModelFactory = PersonajeViewModelFactory(useCase)
        personajesViewModel = ViewModelProvider(this, viewModelFactory).get(PersonajeViewModel::class.java)

        personajeAdapter = PersonajeAdapter()
        binding.recyclerCharacters.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerCharacters.adapter = personajeAdapter

        personajesViewModel.allPersonaje.observe(viewLifecycleOwner) { personajes ->
            personajeAdapter.personajes = personajes.toMutableList()
            personajeAdapter.notifyDataSetChanged()
        }

        personajeAdapter.onItemClickListener = { personaje ->
            val bundle = Bundle().apply {
                putParcelable("personaje", personaje)
            }
            navController.navigate(R.id.detallePersonajeFragment, bundle)
        }


    }


}
