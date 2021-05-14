package com.kwartracker.android.dashboard.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentDashboardBinding
import com.kwartracker.android.databinding.FragmentWalletsBinding

class DashboardFragment : Fragment() {

    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBarDrawerToggle = setupDrawerToggle()
        actionBarDrawerToggle?.let { binding.drawerLayout.addDrawerListener(it) }
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)
        binding.drawerLayout.drawerElevation = 0f
        binding.navView.inflateHeaderView(R.layout.navigation_header)
        binding.btnDrawer.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle? {
        return object : ActionBarDrawerToggle(requireActivity(), binding.drawerLayout, null, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                binding.linearlayoutMainMenu.translationX = slideOffset * drawerView.width
                binding.drawerLayout.bringChildToFront(drawerView)
                binding.drawerLayout.requestLayout()
            }

        }
    }
}
