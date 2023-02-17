package com.kagiya.pokedex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.kagiya.pokedex.databinding.ItemOnboardingBinding


class OnboardingHolder(val binding: ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root){

}


class OnboardingAdapater(
    val images: List<Int>,
    val titles: List<Int>,
    val descriptions: List<Int>
): RecyclerView.Adapter<OnboardingHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOnboardingBinding.inflate(inflater, parent, false)
        return OnboardingHolder(binding)
    }


    override fun getItemCount(): Int {
        return images.size
    }


    override fun onBindViewHolder(holder: OnboardingHolder, position: Int) {
        val currentImage = images[position]
        val currentTitle = titles[position]
        val currentDescription = descriptions[position]

        holder.binding.onboardingImage.setImageResource(currentImage)
        holder.binding.onboardingTitle.setText(currentTitle)
        holder.binding.onboardingDescription.setText(currentDescription)
    }
}