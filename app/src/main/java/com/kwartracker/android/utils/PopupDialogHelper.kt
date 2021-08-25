package com.kwartracker.android.utils

import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import com.kwartracker.android.R

object PopupDialogHelper {

    fun showMenu(view: View, @MenuRes menuRes: Int, function: (String) -> Unit) {
        val ctw = ContextThemeWrapper(view.context, R.style.Transaction_Popup_Category)
        val popup = PopupMenu(ctw, view)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { item ->

            function.invoke(item.title.toString())
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }

}
