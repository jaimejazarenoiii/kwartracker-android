package com.kwartracker.android.login.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentLoginBinding
import com.kwartracker.android.login.viewmodel.LoginViewModel
import com.kwartracker.android.utils.extension.handleApolloResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by viewModels()

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        observers()
        handleApolloResponse(viewModel.userLogin)
    }

    override fun onResume() {
        super.onResume()
        binding.nsvContents.post {
            binding.nsvContents.fling(0)
            binding.nsvContents.fullScroll(ScrollView.FOCUS_UP)
        }
    }

    private fun observers() {
        viewModel.emailAddress.observe(
            viewLifecycleOwner,
            {
                viewModel.loginDataChanged()
            }
        )

        viewModel.password.observe(
            viewLifecycleOwner,
            {
                viewModel.loginDataChanged()
            }
        )

        viewModel.formState.observe(
            viewLifecycleOwner,
            {
                binding.btnSignIn.backgroundTintList = if (!it.isValid) {
                    ColorStateList.valueOf(
                        ContextCompat
                            .getColor(requireActivity(), R.color.gray)
                    )
                } else {
                    ColorStateList.valueOf(
                        ContextCompat
                            .getColor(requireActivity(), R.color.app_color)
                    )
                }
            }
        )
    }
}
