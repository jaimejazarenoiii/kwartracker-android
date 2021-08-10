package com.kwartracker.android

import com.google.common.truth.Truth.assertThat
import com.kwartracker.android.utils.StringValidator
import org.junit.Before
import org.junit.Test

class LoginUnitTest {

    private lateinit var validator: StringValidator

    @Before
    fun setup() {
        validator = StringValidator()
    }

    @Test
    fun isValidEmail_ReturnsTrue() {
        val emailValidator = validator
            .required()
            .email()
            .lengthRange(8, 50)
        val result = emailValidator.validate("lariosajupilot1@gmail.com")
        assertThat(result).isTrue()
    }

    @Test
    fun isInvalidEmail_ReturnsFalse() {
        val emailValidator = validator
            .required()
            .email()
            .lengthRange(8, 50)
        val result = emailValidator.validate("thisisnotanemail")
        assertThat(result).isFalse()
    }

    @Test
    fun isEmptyEmail_ReturnsFalse() {
        val emailValidator = validator
            .required()
            .email()
            .lengthRange(8, 50)
        val result = emailValidator.validate("")
        assertThat(result).isFalse()
    }

    @Test
    fun isValidPassword_ReturnTrue() {
        val passWordValidator = validator
            .required()
            .lengthRange(8, 50)
        val result = passWordValidator.validate("Pilipin0")
        assertThat(result).isTrue()
    }

    @Test
    fun isEmptyPassword_ReturnFalse() {
        val passWordValidator = validator
            .required()
            .lengthRange(8, 50)
        val result = passWordValidator.validate("")
        assertThat(result).isFalse()
    }

    @Test
    fun isPasswordOutOflength_ReturnFalse() {
        val passWordValidator = validator
            .required()
            .lengthRange(8, 50)
        val result = passWordValidator.validate("Pass")
        assertThat(result).isFalse()
    }
}
