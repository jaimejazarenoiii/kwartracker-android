package com.kwartracker.android.widgets

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import com.kwartracker.android.R
import com.kwartracker.android.databinding.ConfirmedDialogBinding

class ConfirmedDialog(activity: Activity) : Dialog(activity), View.OnClickListener {
    var dialog: Dialog? = null
    var title: String? = null
    var message: String? = null
    var exitText: String? = null
    lateinit var scrim: LinearLayout
    lateinit var llDialog: LinearLayout
    lateinit var ivDialogIcon: ImageView
    lateinit var binding: ConfirmedDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmedDialogBinding.inflate(layoutInflater)
        scrim = binding.scrim
        ivDialogIcon = binding.ivDialogIcon
        llDialog = binding.llDialog

        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        scrim.setOnClickListener(this)
        llDialog.setOnClickListener(this)
    }

    private fun init() {
        if (title != null) binding.tvTitle.text = title
        if (message != null) binding.tvMessage.text = message
        if (exitText != null) binding.btnDialogExit.text = exitText
    }

    override fun show() {
        super.show()
        init()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_cancel -> dismiss()
            R.id.scrim -> dismiss()
            else -> {}
        }
    }
}
