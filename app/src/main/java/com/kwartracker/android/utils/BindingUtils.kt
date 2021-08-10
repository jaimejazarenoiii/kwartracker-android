@file:JvmName("BindingUtils")
package com.kwartracker.android.utils

import androidx.databinding.InverseMethod

@InverseMethod("positionToGender")
fun genderToPosition(gender: GenderType?): String? {
    return gender?.value ?: "Male"
}

fun positionToGender(gender: String): GenderType {
    return GenderType.get(gender)
}
