package com.kwartracker.android.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentLoginBinding
import com.kwartracker.android.main.ui.MainActivity
import com.kwartracker.android.signup.ui.SignUpFragment
import com.kwartracker.android.transactions.ui.filter.TransactionsFilterFragment

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignUp.setOnClickListener {
            (activity as MainActivity)?.changeFragment(SignUpFragment())
        }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.walletsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.nsvContents.post {
            binding.nsvContents.fling(0)
            binding.nsvContents.fullScroll(ScrollView.FOCUS_UP)
        }
    }
}
