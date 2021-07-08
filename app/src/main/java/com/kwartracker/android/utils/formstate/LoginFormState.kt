package com.kwartracker.android.utils.formstate

data class LoginFormState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val isValid: Boolean = false
)
