package com.kagiya.pokedex.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kagiya.pokedex.models.DoubleDamageFrom
import com.kagiya.pokedex.databinding.ListItemWeaknessesBinding
import com.kagiya.pokedex.util.BackgroundUtils

class WeaknessesAdapter(private val weaknessesList: List<DoubleDamageFrom>) :
    RecyclerView.Adapter<WeaknessesAdapter.WeaknessViewHolder>() {

    inner class WeaknessViewHolder(private val binding: ListItemWeaknessesBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(weakness: DoubleDamageFrom) {


            val weaknessName = weakness.name
            binding.typeName.text = weaknessName.capitalize()

            val backgroundProperties = BackgroundUtils.getBackgroundProperties(weaknessName)
            binding.type.background.setTint(Color.parseColor(backgroundProperties.typeBackgroundColor))
            binding.typeImage.setImageResource(backgroundProperties.typeImage)
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







