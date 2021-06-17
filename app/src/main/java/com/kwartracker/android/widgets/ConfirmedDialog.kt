package com.kwartracker.android.widgets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.kwartracker.android.R
import com.kwartracker.android.databinding.DialogConfirmedBinding

class ConfirmedDialog : DialogFragment(), View.OnClickListener {
    private lateinit var binding: DialogConfirmedBinding
    private val args: ConfirmedDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_confirmed,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scrim = binding.scrim
        val btnExit = binding.btnDialogExit
        binding.tvTitle.text = args.title
        binding.tvMessage.text = args.message
        binding.btnDialogExit.text = args.txtExit
        binding.ivDialogIcon.setBackgroundResource(args.resID)
        btnExit.setOnClickListener(this)
        scrim.setOnClickListener(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_FRAME, R.style.ConfirmationDialog)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_dialog_exit -> dismiss()
            R.id.scrim -> dismiss()
            else -> {}
        }
    }
}
