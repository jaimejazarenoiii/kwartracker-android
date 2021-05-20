package com.kwartracker.android.transactions.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentAddWalletTransactionBinding
import com.kwartracker.android.transactions.ui.list.ListTransactionFragment
import com.kwartracker.android.transactions.ui.main.TransactionFragment

class AddWalletTransactionFragment : Fragment() {
    lateinit var binding: FragmentAddWalletTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_wallet_transaction,
            container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.transaction_add_detail_fragment)
        }

        binding.btnBack.setOnClickListener {
            TransactionFragment().bottomMainSheetModal(ListTransactionFragment())
        }
    }
}
