package com.kagiya.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kagiya.pokedex.BuildConfig
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseReference: DatabaseReference
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
            setupLogoutButton(firebaseAuth)
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

        setUsername(user?.uid.toString())
    }

    private fun setUsername(userId: String){

        firebaseReference = FirebaseDatabase.getInstance().getReference("Users")

        val userName = firebaseReference.child(userId).get().addOnCompleteListener{
            if(it.isSuccessful){
                binding.name.text = it.result.value.toString()
                binding.nameDetailsTextView.text = it.result.value.toString()
            }
        }
    }

    private fun setupLogoutButton(firebaseAuth: FirebaseAuth) {
        binding.logoutButton.setOnClickListener{
            firebaseAuth.signOut()
            findNavController().navigate(
                R.id.mainActivity
            )
        }
    }
}