package com.kwartracker.android.transactions.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentAddDetailTransactionBinding

class AddDetailTransactionFragment : Fragment() {
    lateinit var binding: FragmentAddDetailTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_detail_transaction,
            container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAdd.setOnClickListener {
            val action = AddDetailTransactionFragmentDirections
                .actionAddDetailTransactionFragmentToConfirmedDialogTransaction(
                    getString(R.string.lbl_success),
                    getString(R.string.lbl_confirmed_success_message),
                    getString(R.string.lbl_cool),
                    R.drawable.ic_success_teal_75
                )
            findNavController().navigate(action)
        }
    }
}
