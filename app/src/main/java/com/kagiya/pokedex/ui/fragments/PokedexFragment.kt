package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kagiya.pokedex.adapters.PokedexAdapter
import com.kagiya.pokedex.models.PokemonDetails
import com.kagiya.pokedex.databinding.FragmentPokedexBinding
import com.kagiya.pokedex.viewmodels.PokedexViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


private const val TAG = "PokedexFragment"

@AndroidEntryPoint
class PokedexFragment : Fragment() {

    private val pokedexViewModel : PokedexViewModel by viewModels()
    private lateinit var binding: FragmentPokedexBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        pokedexViewModel.getPokemonList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        binding.pokedexList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                pokedexViewModel.pokemonDetailedList.collect { details ->

                    if (binding.pokedexList.adapter != null){

                        val adapter: PokedexAdapter = binding.pokedexList.adapter as PokedexAdapter
                        adapter.updateData(details)
                    }
                    else{
                        setupRecyclerView(details)
                    }
                }
            }
        }


        binding.searchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "QueryTextSubmit: $query")
                pokedexViewModel.setQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, newText ?: "")

                if(newText != null){
                    if(newText.isEmpty()){
                        pokedexViewModel.setQuery("")
                    }
                }
                return false
            }
        })
    }


    val scrollListener = object : RecyclerView.OnScrollListener(){

        var isScrolling = false

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = binding.pokedexList.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isAtTheLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 10
            val shouldPaginate = isAtTheLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if(shouldPaginate){
                pokedexViewModel.getPokemonList()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }
    private fun setupRecyclerView(details: List<PokemonDetails>){
        binding.pokedexList.adapter = PokedexAdapter(details) { pokemonName ->
            findNavController().navigate(
                PokedexFragmentDirections.showPokemonDetails(pokemonName)
            )
        }

        binding.pokedexList.addOnScrollListener(scrollListener)
    }
}