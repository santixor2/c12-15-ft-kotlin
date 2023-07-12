package com.nocountry.movie_no_country

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.nocountry.movie_no_country.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenSplash.setKeepOnScreenCondition{ false}

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.favoriteFragment,
                R.id.profileFragment
            )
        )

        navHostFragment.navController.apply {
            binding.bottomNavigation.setupWithNavController(this)

            if (getPrefs()) {
                navigate(R.id.action_onboardingFragment_to_fragment_Login)
            } else {
                navigate(R.id.action_onboardingFragment_self)
            }

        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    fun showBottomNav(show: Boolean) {
        binding.bottomNavigation.isVisible = show
    }
    private fun getPrefs() = getPreferences(Context.MODE_PRIVATE).getBoolean("onBoardingFinished", false)
}