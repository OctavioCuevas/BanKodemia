package com.honeykoders.bankodemia.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val fragmentInicio: InicioFragment = InicioFragment()
    val fragmentTarjeta: Fragment_tarjeta = Fragment_tarjeta()
    val fragmentServicios: ServiciosFragment = ServiciosFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_home_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        setupBottomNavMenu(navController)

    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.setupWithNavController(navController)
    }

}