package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kagiya.pokedex.BuildConfig
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentNotLoggedInProfileBinding

class NotLoggedInProfileFragment : Fragment() {


    private lateinit var binding: FragmentNotLoggedInProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotLoggedInProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginOrRegisterButton.setOnClickListener{
            findNavController().navigate(
                R.id.loginFragment
            )
        }

        binding.appVersionTextView.text = BuildConfig.VERSION_NAME
    }
}