package com.kwartracker.android.widgets

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton
import com.kwartracker.android.R
import com.kwartracker.android.databinding.ConfirmationDialogBinding

class ConfirmationDialog(activity: Activity) : Dialog(activity), View.OnClickListener {
    var dialog: Dialog? = null
    var title: String? = null
    var message: String? = null
    lateinit var yes: MaterialButton
    lateinit var no: MaterialButton
    lateinit var binding: ConfirmationDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmationDialogBinding.inflate(layoutInflater)
        yes = binding.btnYes
        no = binding.btnCancel

        setContentView(binding.root)
        window?.setBackgroundDrawableResource(R.color.tranparent_white)

        if (title != null) binding.tvTitle.text = title
        if (message != null) binding.tvMessage.text = message
        no.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_cancel -> dismiss()
            else -> {}
        }
    }
}
