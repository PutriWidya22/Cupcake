package com.putri.cupcake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

// membuat class MainActivity
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // onCreateView berjalan saat membuat aktivitas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mengakses layout activity_main dan menggunakan navHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // menyiapkan navController yang telah diatur
        setupActionBarWithNavController(navController)
    }
}