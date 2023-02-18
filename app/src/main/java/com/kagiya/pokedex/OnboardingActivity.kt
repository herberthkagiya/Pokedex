package com.kagiya.pokedex

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kagiya.pokedex.databinding.ActivityOnboardingBinding


class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnboardingItems()
    }

    private fun setOnboardingItems(){
        val onboardingItems = listOf(
            OnboardingItem(
                R.drawable.onboarding_1,
                R.string.first_slide_title,
                R.string.first_slide_desc,
                R.string.continue_button
            ) {
                Log.d("Onboarding", "Continue Button Pressed")
            },

            OnboardingItem(
                R.drawable.onboarding_2,
                R.string.second_slide_title,
                R.string.second_slide_desc,
                R.string.let_get_started_button
            ) {
                Log.d("Onboarding", "Let Get Started Button Pressed")
            }
        )

        val adapter = OnboardingAdapater(onboardingItems)
        binding.slider.adapter = adapter
    }
}