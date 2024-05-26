package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Personaje

class PersonajeAdapter(private val personajes: MutableList<Personaje>) : RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder>() {

    inner class PersonajeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterImage: ImageView = view.findViewById(R.id.character_image)
        val txtName: TextView = view.findViewById(R.id.txt_name)
        val txtSpecie: TextView = view.findViewById(R.id.txt_specie)
        val txtGender: TextView = view.findViewById(R.id.txt_gender)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.personaje_item, parent, false)
        return PersonajeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje = personajes[position]
        holder.txtName.text = personaje.name
        holder.txtSpecie.text = personaje.species
        holder.txtGender.text = personaje.gender
        // Usando Glide o Picasso para cargar la imagen desde una URL
        Glide.with(holder.itemView.context).load(personaje.image).into(holder.characterImage)
    }

    override fun getItemCount(): Int {
        return personajes.size
    }
}