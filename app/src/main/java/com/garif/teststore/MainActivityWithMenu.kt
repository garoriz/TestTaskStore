package com.garif.teststore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.garif.teststore.databinding.ActivityMainWithMenuBinding

class MainActivityWithMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainWithMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWithMenuBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val controller = (supportFragmentManager.findFragmentById(R.id.container)
                as NavHostFragment).navController
        val navView = binding.navView

        navView.setupWithNavController(controller)
    }
}