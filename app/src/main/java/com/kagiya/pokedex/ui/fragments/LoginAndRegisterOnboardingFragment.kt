package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentLoginRegisterOnboardingBinding

class LoginAndRegisterOnboardingFragment : Fragment() {

    private lateinit var binding: FragmentLoginRegisterOnboardingBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginRegisterOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skipButton.setOnClickListener{
            findNavController().navigate(
                R.id.skip_registration
            )
        }

        binding.createAccountButton.setOnClickListener{
            findNavController().navigate(
                R.id.create_account
            )
        }
    }
}