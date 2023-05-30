package com.kagiya.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

    private fun setupBottomNavigationMenu(){
//        binding.bottomNavigationView.itemIconTintList = null
//
//
//        //Configuration of BottomNavigationMenu
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val pokedexFragment = PokedexFragment()
//        val regionsFragment = RegionsFragment()
//        val favoritesFragment = FavoritesFragment()
//        val profileFragment = ProfileFragment()
//
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            var fragment: Fragment = PokedexFragment()
//
//            when (item.itemId) {
//                R.id.pokedex_home -> fragment = pokedexFragment
//                R.id.regions -> fragment = regionsFragment
//                R.id.favorites -> fragment = favoritesFragment
//                R.id.profile -> fragment = profileFragment
//            }
//            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
//            true
//        }
//
//
//        // Set default BottomNavigationMenu Selection
//        binding.bottomNavigationView.selectedItemId = R.id.pokedex_home
    }
}


