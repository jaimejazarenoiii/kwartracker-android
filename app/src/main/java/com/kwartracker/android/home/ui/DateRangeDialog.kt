package com.kwartracker.android.home.ui

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import com.kwartracker.android.R
import com.kwartracker.android.databinding.DialogDateRangeHomeBinding

class DateRangeDialog : DialogFragment() {
    private lateinit var binding: DialogDateRangeHomeBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_date_range_home,
            container,
            false
        )
        bottomSheetBehavior = BottomSheetBehavior.from(binding.navView)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        dialog.window?.setDimAmount(0F)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        binding.scrim.setOnClickListener {
            dismiss()
        }

        binding.ibClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }
}
