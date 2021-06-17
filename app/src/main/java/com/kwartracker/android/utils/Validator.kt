package com.kwartracker.android.utils

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.core.util.PatternsCompat
import com.kwartracker.android.KwartrackerApplication
import com.kwartracker.android.R

open class Validator<T>(@StringRes val label: Int?) {

    private var validators: MutableList<(T) -> Boolean> = mutableListOf()

    @StringRes
    private var message: Int? = null
    private var args: Array<out Any> = arrayOf()

    fun create(validator: (T) -> Boolean, message: Int, vararg args: Any): (T) -> Boolean {
        return {
            if (validator(it)) {
                this.message = null
                this.args = arrayOf()
                true
            } else {
                this.message = message
                this.args = args
                false
            }
        }
    }

    fun add(validator: (T) -> Boolean): Validator<T> {
        validators.add(validator)
        return this
    }

    open fun add(validator: (T) -> Boolean, message: Int, vararg args: Any): Validator<T> {
        return add(create(validator, message, *args))
    }

    fun validate(
        suspect: T,
        messageHandler: ((String?) -> Unit)? = null
    ): Boolean {
        return validators.all { it.invoke(suspect) }.also {
            messageHandler?.invoke(
                message?.let { message ->
                    val context = KwartrackerApplication.context
                    context.getString(message).let { template ->
                        if (template.contains("%s") || template.contains("%1\$s")) {
                            val s = label?.let { context.getString(it) } ?: "value"
                            context.getString(message, s, *args)
                        } else {
                            context.getString(message, *args)
                        }
                    }
                }
            )
        }
    }
}

class StringValidator(@StringRes val x: Int?) : Validator<String>(x) {

    private fun checkData(value: Int?): Int {
        return when (value) {
            R.string.lbl_password -> {
                R.string.validation_password
            }
            R.string.lbl_email -> {
                R.string.validation_email_required
            }
            else -> {
                R.string.validation_required
            }
        }
    }

    private fun checkEmail(value: Int?): Int {
        return if (value == R.string.lbl_email) {
            R.string.validation_email_required
        } else {
            R.string.validation_email
        }
    }

    fun required(): StringValidator {
        return add({ it.isNotEmpty() }, checkData(x)) as StringValidator
    }

    fun email(): StringValidator {
        return add(
            { PatternsCompat.EMAIL_ADDRESS.matcher(it).matches() },
            R.string.validation_email
        ) as StringValidator
    }

    fun maxLength(max: Int): StringValidator {
        return add({ it.length <= max }, R.string.validation_maxlength, max) as StringValidator
    }

    fun lengthRange(min: Int, max: Int): StringValidator {
        return add(
            { it.length in min..max },
            R.string.validation_range,
            min,
            max
        ) as StringValidator
    }

    fun tel(): StringValidator {
        return add(
            { it.isEmpty() || Patterns.PHONE.matcher(it).matches() },
            R.string.validation_tel
        ) as StringValidator
    }
}
