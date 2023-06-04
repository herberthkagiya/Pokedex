package com.kagiya.pokedex.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kagiya.pokedex.data.DoubleDamageFrom
import com.kagiya.pokedex.databinding.ListItemWeaknessesBinding

class WeaknessesAdapter(private val weaknessesList: List<DoubleDamageFrom>) :
    RecyclerView.Adapter<WeaknessesAdapter.WeaknessViewHolder>() {

    inner class WeaknessViewHolder(private val binding: ListItemWeaknessesBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(weakness: DoubleDamageFrom) {

            //Show pokemon type
            val weaknessName = weakness.name
            binding.typeName.text = weaknessName.capitalize()
            val typeBackgroundColor = BackgroundUtils.getTypeBackgroundColor(weaknessName)
            binding.type.background.setTint(Color.parseColor(typeBackgroundColor))
            val pokemonTypeImage = BackgroundUtils.getTypeImage(weaknessName)
            binding.typeImage.setImageResource(pokemonTypeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaknessViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ListItemWeaknessesBinding.inflate(inflater, parent, false)
        return WeaknessViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeaknessViewHolder, position: Int) {
        val weakness = weaknessesList[position]
        holder.bind(weakness)
    }

    override fun getItemCount(): Int {
        return weaknessesList.size
    }
}







