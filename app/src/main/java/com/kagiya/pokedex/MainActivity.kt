package com.kagiya.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kagiya.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Start Splash Screen
        installSplashScreen()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.itemIconTintList = null


        //Configuration of BottomNavigationMenu
        val fragmentManager: FragmentManager = supportFragmentManager
        val pokedexFragment = PokedexFragment()
        val regionsFragment = RegionsFragment()
        val favoritesFragment = FavoritesFragment()
        val profileFragment = ProfileFragment()


        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            var fragment: Fragment = PokedexFragment()

            when (item.itemId) {
                R.id.pokedex_home -> fragment = pokedexFragment
                R.id.regions -> fragment = regionsFragment
                R.id.favorites -> fragment = favoritesFragment
                R.id.profile -> fragment = profileFragment
            }
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            true
        }

        // Set default BottomNavigationMenu Selection
        binding.bottomNavigationView.selectedItemId = R.id.pokedex_home



        binding.button.setOnClickListener{
            var intent = Intent(this@MainActivity, OnboardingActivity::class.java)
            startActivity(intent)
        }
    }

}


