package com.kagiya.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setOnBoardingScreenIfNecessary()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigationMenu()
    }

    private fun setOnBoardingScreenIfNecessary(){
        if(isFirstTimeLaunchingTheApp()){
            showOnboardingScreen()
        }
    }

    private fun isFirstTimeLaunchingTheApp(): Boolean {
        val preference = getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE)
        return !preference.contains("ALREADY_SAW_ONBRANDING_SCREEN")
    }

    private fun showOnboardingScreen() {
        var intent = Intent(this@MainActivity, OnboardingActivity::class.java)
        startActivity(intent)
    }

    private fun setBottomNavigationMenu(){
        binding.bottomNavigationView.itemIconTintList = null

        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val navController = supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()
            when (menuItem.itemId) {
                R.id.pokedex_home -> navController?.navigate(R.id.pokedexFragment)
                R.id.regions -> navController?.navigate(R.id.regionsFragment)
                R.id.favorites -> navController?.navigate(R.id.favoritesFragment)
                R.id.profile -> navController?.navigate(R.id.profileFragment)
            }
            true
        }
    }
}


