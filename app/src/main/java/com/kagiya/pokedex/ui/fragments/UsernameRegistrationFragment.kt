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
import com.kagiya.pokedex.databinding.FragmentRegisterUsernameBinding


class UsernameRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegisterUsernameBinding

    private val args: UsernameRegistrationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterUsernameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener{
            val username = binding.editText.text.toString()

            createAccount(args.email, args.password, username)

            findNavController().navigate(
                UsernameRegistrationFragmentDirections.showAccountCreated()
            )
        }
    }

    fun createAccount(email: String, passsword: String, name: String){
        Log.d("Login", "$email $passsword $name")
    }
}