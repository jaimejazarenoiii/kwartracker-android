package com.kwartracker.android.main.ui

import android.os.*
import androidx.appcompat.app.*
import androidx.databinding.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import com.kwartracker.android.*
import com.kwartracker.android.databinding.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation(){
        navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
    }
}
