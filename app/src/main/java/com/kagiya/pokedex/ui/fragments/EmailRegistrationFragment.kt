package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentEmailRegistrationBinding


class EmailRegistrationFragment : Fragment() {
    private lateinit var binding : FragmentEmailRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
}