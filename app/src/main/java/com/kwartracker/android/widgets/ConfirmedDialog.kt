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
    val binding: ConfirmedDialogBinding = ConfirmedDialogBinding.inflate(layoutInflater)
    lateinit var ivDialogIcon: ImageView
    lateinit var scrim: LinearLayout
    lateinit var llDialog: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        binding.btnDialogExit.setOnClickListener(this)
    }

    fun setIcon(resID: Int) {
        binding.ivDialogIcon.setImageResource(resID)
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setMessage(message: String) {
        binding.tvMessage.text = message
    }

    fun setExitText(exitText: String) {
        binding.btnDialogExit.text = exitText
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_dialog_exit -> dismiss()
            R.id.scrim -> dismiss()
            else -> {}
        }
    }
}
