package com.kagiya.pokedex.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kagiya.pokedex.PokemonRepository
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentPokedexBinding
import com.kagiya.pokedex.viewmodel.PokedexViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "PokedexFragment"

class PokedexFragment : Fragment(R.layout.fragment_pokedex) {

    private val pokedexViewModel : PokedexViewModel by viewModels()

    private var _binding: FragmentPokedexBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokedexBinding.inflate(inflater, container, false)
        binding.pokedexList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                pokedexViewModel.pokemons.collect{ pokemons ->

                    val details = pokedexViewModel.getPokemonDetails(pokemons)
                    binding.pokedexList.adapter = PokedexAdapter(details)
                }
            }
        }

    }
}