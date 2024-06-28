package com.example.rickandmorty.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Personaje
import com.example.rickandmorty.databinding.FragmentDetallePersonajeBinding

import com.example.rickandmorty.presentation.viewmodel.PersonajeViewModel


class DetallePersonajeFragment : Fragment() {


    private lateinit var binding: FragmentDetallePersonajeBinding

    private lateinit var personajesViewModel: PersonajeViewModel
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
        binding = FragmentDetallePersonajeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.volverPersonajes.setOnClickListener{findNavController().navigate(R.id.personajesFragment)}
        val personaje = arguments?.getParcelable<Personaje>("personaje")
        personaje?.let {
            // Usa los datos del personaje
            binding.txtName.text = it.name
            binding.txtSpecie.text = it.species
            binding.txtGender.text = it.gender
            Glide.with(this).load(it.image).centerCrop().into(binding.characterImage)
        }
    }
}