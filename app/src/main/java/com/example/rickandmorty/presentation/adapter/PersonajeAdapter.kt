package com.example.rickandmorty.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Personaje
import com.example.rickandmorty.databinding.PersonajeItemBinding

class PersonajeAdapter() : RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder>() {

    var personajes = mutableListOf<Personaje>()
    lateinit var onItemClickListener: (Personaje) -> Unit

    inner class PersonajeViewHolder(private var bindingItem: PersonajeItemBinding)
        : RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(personaje: Personaje) {
            with(personaje) {
                Glide.with(bindingItem.characterImage).load(personaje.image).centerCrop().into(bindingItem.characterImage)
                bindingItem.txtName.text = personaje.name
                bindingItem.txtSpecie.text = personaje.species
                bindingItem.txtGender.text = personaje.gender
            }

            bindingItem.root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(personaje)
                } else {
                    Log.e("Adapter", "Listener not initialized")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val bindingItem = PersonajeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonajeViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje: Personaje = personajes[position]
        holder.bind(personaje)
    }

    override fun getItemCount(): Int {
        return personajes.size
    }

}