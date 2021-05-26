package com.kwartracker.android.transactions.ui.details

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentDetailsTransactionBinding
import com.kwartracker.android.transactions.ui.main.TransactionFragment
import com.kwartracker.android.widgets.ConfirmationDialog
import com.kwartracker.android.widgets.ConfirmedDialog

class DetailsTransactionFragment : Fragment() {
    lateinit var binding: FragmentDetailsTransactionBinding
    private lateinit var confirmationDialog: ConfirmationDialog
    private lateinit var confirmedDialog: ConfirmedDialog

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
        confirmedDialog = ConfirmedDialog(activity as Activity)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivDelete.setOnClickListener {
            confirmationDialog.title = getString(R.string.title_confirmation)
            confirmationDialog.message = getString(R.string.lbl_message_delete)
            confirmationDialog.show()
            confirmationDialog.yes.setOnClickListener {
                confirmationDialog.dismiss()
                confirmedDialog.ivDialogIcon.setImageResource(R.drawable.ic_delete_red_58)
                confirmedDialog.title = getString(R.string.lbl_deleted)
                confirmedDialog.exitText = getString(R.string.lbl_exit)
                confirmedDialog.message = getString(R.string.lbl_confirmed_delete_message)
                confirmedDialog.show()
            }
        }
    }
}
