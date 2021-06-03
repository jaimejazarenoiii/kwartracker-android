package com.kwartracker.android.transactions.ui.list

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kwartracker.android.R
import com.kwartracker.android.databinding.DialogDateRangeTransactionBinding

class DateRangeDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DialogDateRangeTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_date_range_transaction,
            container,
            false
        )

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(
            R.color.transparent_white
        )
        dialog.window?.setDimAmount(0F)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibClose.setOnClickListener {
            dismiss()
        }
    }
}
