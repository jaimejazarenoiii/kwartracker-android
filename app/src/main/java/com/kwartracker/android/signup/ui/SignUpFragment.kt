package com.kwartracker.android.signup.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kwartracker.android.R

class SignUpFragment : Fragment() {

    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_signup, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_slideshow)
        signUpViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}