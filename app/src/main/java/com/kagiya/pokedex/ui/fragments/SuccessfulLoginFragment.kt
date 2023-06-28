package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentSuccessfulLoginBinding


class SuccessfulLoginFragment : Fragment() {

    private lateinit var binding : FragmentSuccessfulLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessfulLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fisishLoginButton.setOnClickListener{
           findNavController().navigate(
               R.id.finish_login
           )
        }
    }
}