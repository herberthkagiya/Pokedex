package com.kagiya.pokedex.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kagiya.pokedex.R
import com.kagiya.pokedex.adapters.OnboardingAdapater
import com.kagiya.pokedex.databinding.FragmentOnboardingBinding
import com.kagiya.pokedex.models.OnboardingItem


private const val ALREADY_SAW_ONBOARDING_SCREEN = "ALREADY_SAW_ONBOARDING_SCREEN"
private const val USER_PREFERENCES = "USER_PREFERENCES"

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(layoutInflater, container, false)

        setOnboardingItems()

        binding.dotsIndicator.attachTo(binding.slider)

        return binding.root
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
//                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
//                val editor: SharedPreferences.Editor = preferences.edit()
//                editor.putBoolean(ALREADY_SAW_ONBOARDING_SCREEN, true)
//                editor.apply()

                findNavController().navigate(
                    R.id.show_login_and_create_account
                )
            }
        )

        val adapter = OnboardingAdapater(onboardingItems)
        binding.slider.adapter = adapter
    }


}

