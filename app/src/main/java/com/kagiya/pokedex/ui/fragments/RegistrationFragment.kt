package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentRegionsBinding
import com.kagiya.pokedex.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {
    private lateinit var binding : FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueWithEmailButton.setOnClickListener{
            findNavController().navigate(
                R.id.register_account_with_email
            )
        }
    }
}