package com.kagiya.pokedex

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kagiya.pokedex.databinding.ActivityOnboardingBinding


class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = listOf(R.drawable.onboarding_1, R.drawable.onboarding_2)
        val titles = listOf(R.string.first_slide_title, R.string.second_slide_title)
        val descriptions = listOf(R.string.first_slide_desc, R.string.second_slide_desc)

        val adapter = OnboardingAdapater(images, titles, descriptions)

        binding.slider.adapter = adapter
    }

}