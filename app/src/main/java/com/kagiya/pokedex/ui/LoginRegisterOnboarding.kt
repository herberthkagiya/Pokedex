package com.kagiya.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import com.kagiya.pokedex.MainActivity
import com.kagiya.pokedex.databinding.ActivityLoginRegisterOnboardingBinding

class LoginRegisterOnboarding : AppCompatActivity() {

    private lateinit var binding: ActivityLoginRegisterOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginRegisterOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.skipButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        //Disable the back button
        onBackPressedDispatcher.addCallback(this) {
        }
    }
}