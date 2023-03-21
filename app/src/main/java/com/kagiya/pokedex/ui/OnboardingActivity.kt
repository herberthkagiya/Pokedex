package com.kagiya.pokedex.ui

import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.ActivityOnboardingBinding


private const val ALREADY_SAW_ONBRANDING_SCREEN = "ALREADY_SAW_ONBRANDING_SCREEN"
private const val USER_PREFERENCES = "USER_PREFERENCES"

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setOnboardingItems()

        binding.dotsIndicator.attachTo(binding.slider)

        //Disable the back button
        onBackPressedDispatcher.addCallback(this) {
        }
    }

    private fun setOnboardingItems(){

        val onboardingItems = listOf(
            OnboardingItem(
                R.drawable.onboarding_1,
                R.string.first_slide_title,
                R.string.first_slide_desc,
                R.string.continue_button
            ) {
                binding.slider.currentItem += 1
            },

            OnboardingItem(
                R.drawable.onboarding_2,
                R.string.second_slide_title,
                R.string.second_slide_desc,
                R.string.let_get_started_button
            ) {
                val preferences = getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE)
                val editor: Editor = preferences.edit()
                editor.putBoolean(ALREADY_SAW_ONBRANDING_SCREEN, true)
                editor.commit()

                val intent = Intent(this, LoginRegisterOnboarding::class.java)
                startActivity(intent)
            }
        )

        val adapter = OnboardingAdapater(onboardingItems)
        binding.slider.adapter = adapter
    }



}