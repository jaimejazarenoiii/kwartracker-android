package com.kwartracker.android.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import com.kwartracker.android.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}