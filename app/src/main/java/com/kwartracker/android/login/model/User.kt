package com.kwartracker.android.login.model

data class User(
    var id: String? = null,
    var email: String? = null,
    var lastName: String? = null,
    var firstName: String? = null,
    var middleName: String? = null,
    var age: Int? = null,
    var gender: String? = null
)
