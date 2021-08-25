package com.kwartracker.android.utils.formstate

data class SignUpFormState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val confPasswordError: String? = null,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val ageError: String? = null,
    val genderError: String? = null,
    val isValid: Boolean = false
)
