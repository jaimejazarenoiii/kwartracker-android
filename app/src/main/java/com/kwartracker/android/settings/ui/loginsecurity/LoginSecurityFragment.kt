package com.kwartracker.android.settings.ui.loginsecurity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentLoginSecurityBinding

class LoginSecurityFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentLoginSecurityBinding
    private val password = "password"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login_security, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        maskPassword()
        binding.btnBack.setOnClickListener(this)
        binding.ibChangePassword.setOnClickListener(this)
        binding.btnChangePassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBack -> {
                findNavController().popBackStack()
            }
            binding.ibChangePassword,
            binding.btnChangePassword -> {
                findNavController()
                    .navigate(R.id.action_loginSecurityFragment_to_changePasswordFragment)
            }
        }
    }

    private fun maskPassword() {
        for (i in password.toCharArray()) {
            binding.tvPassword.append("\u25CF ")
        }
    }
}
