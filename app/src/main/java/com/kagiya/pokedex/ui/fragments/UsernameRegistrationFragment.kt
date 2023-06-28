package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentRegisterUsernameBinding


class UsernameRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegisterUsernameBinding
    private lateinit var firebaseAuth: FirebaseAuth

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
        }
    }

    fun createAccount(email: String, passsword: String, name: String){
        firebaseAuth = FirebaseAuth.getInstance()


        firebaseAuth.createUserWithEmailAndPassword(email, passsword).addOnCompleteListener{
            if(it.isSuccessful){
                findNavController().navigate(
                    UsernameRegistrationFragmentDirections.showAccountCreated()
                )
            }
            else{
                Log.e("Login", "Erorr at creating account ", it.exception)
            }
        }


        Log.d("Login", "$email $passsword $name")
    }
}