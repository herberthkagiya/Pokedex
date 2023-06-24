package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentPokedexBinding
import com.kagiya.pokedex.databinding.FragmentRegionsBinding

class RegionsFragment : Fragment() {

    private lateinit var binding: FragmentRegionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegionsBinding.inflate(inflater, container, false)
        return binding.root
    }
}