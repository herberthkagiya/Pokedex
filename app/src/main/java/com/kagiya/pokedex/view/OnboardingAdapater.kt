package com.kagiya.pokedex.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kagiya.pokedex.databinding.ItemOnboardingBinding


class OnboardingHolder(val binding: ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: OnboardingItem){
        val currentImage = item.image
        val currentTitle = item.title
        val currentDescription = item.description
        val currentButtonText = item.buttonText

        binding.onboardingImage.setImageResource(currentImage)
        binding.onboardingTitle.setText(currentTitle)
        binding.onboardingDescription.setText(currentDescription)
        binding.onboardingButton.setText(currentButtonText)

        binding.onboardingButton.setOnClickListener{
            item.onButtonClicked()
        }
    }
}


class OnboardingAdapater(
    val items: List<OnboardingItem>
): RecyclerView.Adapter<OnboardingHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOnboardingBinding.inflate(inflater, parent, false)
        return OnboardingHolder(binding)
    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: OnboardingHolder, position: Int) {
        holder.bind(items[position])
    }
}