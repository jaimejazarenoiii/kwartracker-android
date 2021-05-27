package com.kwartracker.android.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener(this)
        binding.btnLoginSecurity.setOnClickListener(this)
        binding.btnCategories.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBack -> {
                findNavController().popBackStack()
            }
            binding.btnLoginSecurity -> {
                findNavController().navigate(R.id.action_settingsFragment_to_loginSecurityFragment)
            }
            binding.btnCategories -> {
                Toast.makeText(activity, "No Categories UI", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
