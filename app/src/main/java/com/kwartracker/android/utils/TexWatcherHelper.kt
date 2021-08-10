package com.kwartracker.android.utils

import android.text.Editable
import android.text.TextWatcher

class TexWatcherHelper(val data: () -> Unit) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        data.invoke()
    }
}
