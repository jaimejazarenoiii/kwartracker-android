package com.kwartracker.android.transactions.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionEditBinding
import com.kwartracker.android.transactions.ui.list.TransactionsListFragment
import com.kwartracker.android.transactions.ui.main.TransactionsFragment

class TransactionEditFragment : Fragment() {
    lateinit var binding: FragmentTransactionEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transaction_edit,
            container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener {
            TransactionsFragment().bottomMainSheetModal(TransactionsListFragment())
        }
    }
}
