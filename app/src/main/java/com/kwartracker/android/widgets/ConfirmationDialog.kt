package com.kwartracker.android.widgets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.kwartracker.android.R
import com.kwartracker.android.databinding.DialogConfirmationBinding

class ConfirmationDialog : DialogFragment(), View.OnClickListener {
    private val args: ConfirmationDialogArgs by navArgs()
    private lateinit var binding: DialogConfirmationBinding
    private lateinit var no: MaterialButton
    private lateinit var yes: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_confirmation,
            container,
            false
        )
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_FRAME, R.style.ConfirmationDialog)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scrim = binding.scrim
        no = binding.btnCancel
        yes = binding.btnYes
        binding.tvTitle.text = args.title
        binding.tvMessage.text = args.message
        no.setOnClickListener(this)
        yes.setOnClickListener(this)
        scrim.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_cancel -> dismiss()
            R.id.scrim -> dismiss()
            R.id.btn_yes -> {
                findNavController()
                    .previousBackStackEntry
                    ?.savedStateHandle?.set("key", 1)
                findNavController().popBackStack()
            }
            else -> {}
        }
    }
}
