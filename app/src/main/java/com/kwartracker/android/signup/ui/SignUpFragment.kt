package com.kwartracker.android.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentSignupBinding
import com.kwartracker.android.signup.viewmodel.SignUpViewModel
import com.kwartracker.android.utils.PopupDialogHelper.showMenu
import com.kwartracker.android.utils.extension.setBackgroundTint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tvSignin.setOnClickListener {
            findNavController().popBackStack(R.id.loginFragment, false)
        }
        viewModel.formState.observe(viewLifecycleOwner) {
            binding.btnSignup.setBackgroundTint(it.isValid)
        }
        binding.tvGender.setOnClickListener { tv ->
            showMenu(tv, R.menu.menu_genders) { value ->
                val texView = tv as TextView
                texView.text = value
            }
        }
    }
}
