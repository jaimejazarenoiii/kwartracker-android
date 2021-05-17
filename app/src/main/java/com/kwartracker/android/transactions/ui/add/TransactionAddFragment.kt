package com.kwartracker.android.transactions.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionAddBinding
import com.kwartracker.android.transactions.ui.list.TransactionsListFragment
import com.kwartracker.android.transactions.ui.main.TransactionsFragment

class TransactionAddFragment : Fragment() {
    lateinit var binding: FragmentTransactionAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_add, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.transaction_add_1_fragment)
        }
        binding.btnBack.setOnClickListener {
            TransactionsFragment().bottomMainSheetModal(TransactionsListFragment())
        }
    }
}
