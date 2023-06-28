package com.kagiya.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kagiya.pokedex.R
import com.kagiya.pokedex.databinding.ActivityMainBinding

private const val ALREADY_SAW_ONBOARDING_SCREEN = "ALREADY_SAW_ONBOARDING_SCREEN"
private const val USER_PREFERENCES = "USER_PREFERENCES"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnBoardingScreenIfNecessary()

        setupBottomNavigationMenu()
    }


    private fun setOnBoardingScreenIfNecessary(){
        if(isFirstTimeLaunchingTheApp()){
            showOnboardingScreen()
        }
    }

    private fun isFirstTimeLaunchingTheApp(): Boolean {
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        return !preference.contains(ALREADY_SAW_ONBOARDING_SCREEN)
    }

    private fun showOnboardingScreen() {
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()

        navController?.navigate(R.id.onboardingFragment)
    }

    private fun setupBottomNavigationMenu(){
        binding.bottomNavigationView.itemIconTintList = null

        changeFragmentDependingOnMenuSelectedItem()

        removeMenuDependingOnFragmentThatIsOnScreen()
    }

    private fun changeFragmentDependingOnMenuSelectedItem(){

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


    private fun removeMenuDependingOnFragmentThatIsOnScreen(){

        val bottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.fragment_container)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){
                R.id.pokemonDetailsFragment -> bottomNavigationView.visibility = View.GONE
                R.id.onboardingFragment -> bottomNavigationView.visibility = View.GONE
                R.id.loginAndRegisterOnboardingFragment -> bottomNavigationView.visibility = View.GONE
                R.id.registrationFragment -> bottomNavigationView.visibility = View.GONE
                R.id.emailRegistrationFragment -> bottomNavigationView.visibility = View.GONE
                R.id.passwordRegistrationFragment -> bottomNavigationView.visibility = View.GONE
                R.id.usernameRegistrationFragment -> bottomNavigationView.visibility = View.GONE
                R.id.accountCreatedFragment -> bottomNavigationView.visibility = View.GONE
                R.id.loginFragment -> bottomNavigationView.visibility = View.GONE
                R.id.loginInputFragment -> bottomNavigationView.visibility = View.GONE
                R.id.successfulLoginFragment -> bottomNavigationView.visibility = View.GONE
                else ->  bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}


