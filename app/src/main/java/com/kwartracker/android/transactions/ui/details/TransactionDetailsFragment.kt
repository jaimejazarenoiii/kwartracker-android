package com.kwartracker.android.transactions.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionDetailsBinding
import com.kwartracker.android.transactions.ui.main.TransactionsFragment

class TransactionDetailsFragment : Fragment() {
    lateinit var binding: FragmentTransactionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_transaction_details,
            container, false)
        TransactionsFragment().tbTitle?.text = "Transaction"

        return binding.root
    }
}
