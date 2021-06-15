package com.kwartracker.android.transactions.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentDetailsTransactionBinding
import com.kwartracker.android.transactions.ui.main.TransactionFragment
import com.kwartracker.android.transactions.ui.main.TransactionFragmentDirections

class DetailsTransactionFragment : Fragment() {
    lateinit var binding: FragmentDetailsTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details_transaction,
            container, false
        )
        TransactionFragment().tbTitle?.text = getString(R.string.title_transaction)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivDelete.setOnClickListener {
            val action = TransactionFragmentDirections
                .actionTransactionFragmentToConfirmationDialogDeleteTransaction(
                    getString(R.string.title_confirmation),
                    getString(R.string.lbl_message_delete)
                )
            findNavController().navigate(action)
        }

        // navigate after confirmation
        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Int>("key")?.observe(viewLifecycleOwner) { }
    }
}
