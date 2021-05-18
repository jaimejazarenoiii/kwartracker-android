package com.kwartracker.android.settings.loginSecurity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentLoginSecurityBinding

class LoginSecurityFragment : Fragment() {
    lateinit var binding: FragmentLoginSecurityBinding
    val password = "password"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login_security, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        maskPassword()
    }

    private fun maskPassword() {
        for (i in password.toCharArray()) {
            binding.tvPassword.append("\u25CF ")
        }
    }
}
