package com.kwartracker.android.transactions.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionAdd2Binding

class TransactionAddFragmentS2 : Fragment() {
    lateinit var binding: FragmentTransactionAdd2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_add_2, container, false)

        return binding.root
    }
}
