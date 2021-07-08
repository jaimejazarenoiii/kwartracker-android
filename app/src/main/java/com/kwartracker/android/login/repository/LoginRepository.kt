package com.kwartracker.android.login.repository

import com.kwartracker.android.LoginMutation
import com.kwartracker.android.login.model.User

interface LoginRepository {

    suspend fun loginUser(email: String, password: String): LoginMutation.Data?

    suspend fun logIn(email: String, password: String): User?
}
