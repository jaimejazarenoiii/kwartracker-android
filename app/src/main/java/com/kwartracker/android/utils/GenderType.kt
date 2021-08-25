package com.kwartracker.android.utils

enum class GenderType(
    val raw: Int,
    val value: String,
) {
    MALE(
        0,
        "Male"
    ),
    FEMALE(
        1,
        "Female"
    );

    companion object {

        fun get(gender: String): GenderType {
            return values().firstOrNull {
                it.value == gender
            } ?: MALE
        }
    }
}
