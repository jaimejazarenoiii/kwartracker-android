package com.kwartracker.android.login.ui

import android.os.*
import android.view.*
import android.widget.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import com.kwartracker.android.*
import com.kwartracker.android.databinding.*

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
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
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
