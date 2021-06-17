package com.kwartracker.android.transactions.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentEditTransactionBinding

class EditTransactionFragment : Fragment() {
    lateinit var binding: FragmentEditTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_transaction,
            container, false
        )

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}
