@file:JvmName("BindingUtils")

package com.kwartracker.android.utils

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod

@InverseMethod("positionToGender")
fun genderToPosition(gender: GenderType?): String? {
    return gender?.value ?: "Male"
}

fun positionToGender(gender: String): GenderType {
    return GenderType.get(gender)
}

@BindingAdapter("onValidateFields")
fun EditText.onValidateFields(textWatcher: TextWatcher) {
    addTextChangedListener(textWatcher)
}
