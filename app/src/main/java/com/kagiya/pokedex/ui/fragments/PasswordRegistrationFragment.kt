package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentPasswordRegistrationBinding


class PasswordRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentPasswordRegistrationBinding

    private val args: PasswordRegistrationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueToRegisterUsernameButton.setOnClickListener{
            val password = binding.editText.text.toString()

            findNavController().navigate(
                PasswordRegistrationFragmentDirections.registerUsername(args.email, password)
            )
        }

    }
}