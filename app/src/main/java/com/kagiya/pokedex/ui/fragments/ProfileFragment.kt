package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kagiya.pokedex.BuildConfig
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.appVersionTextView.text = BuildConfig.VERSION_NAME

        if(!isUserLogged()){
            findNavController().navigate(
                R.id.notLoggedInProfileFragment
            )
        }
        else{
            setUserInformation()
        }
    }

    private fun isUserLogged() : Boolean{

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        return user != null
    }

    private fun setUserInformation(){
        firebaseAuth = FirebaseAuth.getInstance()

        val user = firebaseAuth.currentUser

        binding.emailTextView.text = user?.email
    }
}