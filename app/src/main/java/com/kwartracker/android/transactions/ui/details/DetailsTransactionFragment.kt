package com.kwartracker.android.transactions.ui.details

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentDetailsTransactionBinding
import com.kwartracker.android.transactions.ui.main.TransactionFragment
import com.kwartracker.android.widgets.ConfirmationDialog

class DetailsTransactionFragment : Fragment() {
    lateinit var binding: FragmentDetailsTransactionBinding
    private lateinit var confirmationDialog: ConfirmationDialog

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
        confirmationDialog = ConfirmationDialog(activity as Activity)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivDelete.setOnClickListener {
            confirmationDialog.title = getString(R.string.title_confirmation)
            confirmationDialog.message = "Are you sure you want to delete this?"
            confirmationDialog.show()
            confirmationDialog.yes.setOnClickListener {
                Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
