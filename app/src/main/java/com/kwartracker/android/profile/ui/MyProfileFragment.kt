package com.kwartracker.android.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener(this)
        binding.ibEditProfile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBack -> {
                findNavController().popBackStack()
            }
            binding.ibEditProfile -> {
                findNavController().navigate(R.id.action_profile_fragment_to_editProfileFragment)
            }
        }
    }
}
