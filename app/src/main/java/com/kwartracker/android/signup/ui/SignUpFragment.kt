package com.kwartracker.android.signup.ui

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentLoginBinding
import com.kwartracker.android.databinding.FragmentSignupBinding


class SignUpFragment : Fragment() {

    private lateinit var signUpViewModel: SignUpViewModel
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
        binding.tvSignin.setOnClickListener {
            findNavController().popBackStack(R.id.loginFragment, false)
        }
    }
}
