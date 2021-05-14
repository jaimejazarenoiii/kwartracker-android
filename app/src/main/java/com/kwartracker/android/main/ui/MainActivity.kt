package com.kwartracker.android.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kwartracker.android.R
import com.kwartracker.android.databinding.ActivityMainBinding
import com.kwartracker.android.login.ui.LoginFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
        setupToolbar()
        changeFragment(LoginFragment(), R.id.nav_host_fragment)
        bottomSheetModal(null)
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar
        val drawerLayout = binding.drawerLayout
        val navMain = binding.navMain
        setSupportActionBar(binding.toolbar)
        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navMain.setNavigationItemSelectedListener { menuItem ->
            menuItem.itemId
            menuItem.isChecked = true
            drawerLayout.close()

            when (menuItem.itemId) {
                R.id.transactions_fragment ->
                    navHostFragment.findNavController().navigate(R.id.transaction_lists_fragment)
                R.id.wallets_fragment ->
                    navHostFragment.findNavController().navigate(R.id.walletsFragment)
                R.id.profile_fragment ->
                    navHostFragment.findNavController().navigate(R.id.myProfileFragment)
                else -> {
                    true
                }
            }

            true
        }
    }

    @SuppressLint("RestrictedApi")
    private fun setupNavigation() {
        val navFragmentMain = binding.navHostFragmentMain
        val navFragment = binding.navHostFragment
        val navBackdrop = binding.navBackdrop
        val ablMain = binding.ablMain

        navHostFragment = supportFragmentManager
            .findFragmentById(navFragmentMain.id) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->

            val currentFragment = resources.getResourceEntryName(destination.id)
            if (currentFragment == "loginFragment" ||
                currentFragment == "signupFragment") {
                navFragment.visibility = View.VISIBLE
                navBackdrop.visibility = View.GONE
                ablMain.visibility = View.GONE
            } else {
                navFragment.visibility = View.GONE
                navBackdrop.visibility = View.VISIBLE
                ablMain.visibility = View.VISIBLE

                val bottomSheetBehavior = BottomSheetBehavior.from(binding.navBackdrop)
                bottomSheetBehavior.skipCollapsed = true
                bottomSheetBehavior.isHideable = true
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                        bottomSheetBehavior.skipCollapsed = true
                        bottomSheetBehavior.isHideable = false
                    }, 500
                )
            }
        }

        navFragment.visibility = View.VISIBLE
        navBackdrop.visibility = View.GONE
        ablMain.visibility = View.GONE
    }

    fun changeFragment(fragment: Fragment, navID: Int = R.id.nav_host_fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val newFragment: Fragment = fragment

        transaction.replace(navID, newFragment)
        transaction.commit()
    }

    fun bottomSheetModal(fragment: Fragment?) {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.nvFilter)
        val scrim = binding.scrim

        scrim.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == 5) scrim.visibility = View.GONE
                else scrim.visibility = View.VISIBLE
            }
        })

        if (fragment != null) {
            scrim.visibility = View.VISIBLE
            changeFragment(fragment, R.id.nav_fragment_modal)
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }, 500
            )
        }
    }
}
