package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentLoginInputBinding

private const val TAG = "LoginInputFragment"

class LoginInputFragment : Fragment() {

    private lateinit var binding : FragmentLoginInputBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener{
            try {
                executeLogin()
            }
            catch (ex: Exception){
                Log.d(TAG, "Error at login", ex)
            }
        }
    }

    private fun executeLogin(){
        firebaseAuth = FirebaseAuth.getInstance()

        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if(isEmailValid(email) && isPasswordValid(password)){

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                if(it.isSuccessful){
                    findNavController().navigate(
                        R.id.show_successful_login_screen
                    )
                }
                else{
                    Log.d(TAG, "Error at login", it.exception)
                }
            }
        }

    }

    private fun isEmailValid(email: String) : Boolean{
        return email.isNotEmpty()
    }

    private fun isPasswordValid(password: String) : Boolean{
        return password.isNotEmpty() && password.length > 6
    }
}