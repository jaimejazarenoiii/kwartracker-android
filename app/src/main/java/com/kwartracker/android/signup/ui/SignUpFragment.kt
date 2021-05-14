package com.kwartracker.android.signup.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentSignupBinding
import com.kwartracker.android.login.ui.LoginFragment
import com.kwartracker.android.main.ui.MainActivity

class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.hide()
        binding.tvSignin.setOnClickListener {
            (activity as MainActivity)?.changeFragment(LoginFragment())
        }
    }
}
