package com.kwartracker.android.signup.repository

import com.kwartracker.android.SignupMutation
import com.kwartracker.android.login.model.User

interface SignupRepository {

    suspend fun signUp(request: HashMap<String, Any>): SignupMutation.Data?

    suspend fun register(request: HashMap<String, Any>): User?
}
