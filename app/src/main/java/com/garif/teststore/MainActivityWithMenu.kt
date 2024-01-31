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
        val inflater = controller.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph_with_menu)

        when (intent?.extras?.getBoolean(getString(com.garif.core.R.string.is_load_catalog))) {
            true -> {
                graph.setStartDestination(R.id.navigation_catalog)
            }

            else -> {
                graph.setStartDestination(R.id.navigation_main)
            }
        }

        controller.setGraph(graph, intent.extras)
        navView.setupWithNavController(controller)
    }
}