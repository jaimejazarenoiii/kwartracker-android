package com.kwartracker.android.signup.ui

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import com.kwartracker.android.*
import com.kwartracker.android.databinding.*

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
