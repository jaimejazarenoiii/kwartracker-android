package com.kwartracker.android.login.repository

import com.apollographql.apollo.coroutines.await
import com.kwartracker.android.LoginMutation
import com.kwartracker.android.login.model.User
import com.kwartracker.android.type.CredentialsInput
import com.kwartracker.android.utils.ApiHelper.safeApiCall
import com.kwartracker.android.utils.network.KwartrackerApi
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class LoginRepositoryImpl @Inject constructor(
    private val api: KwartrackerApi
) : LoginRepository {

    override suspend fun loginUser(email: String, password: String): LoginMutation.Data? {
        return safeApiCall(Dispatchers.Main) {
            api.getApolloClient()
                .mutate(LoginMutation(CredentialsInput(email = email, password = password)))
                .await()
        }
    }

    override suspend fun logIn(email: String, password: String): User? {
        return loginUser(email, password)?.signInWithEmail?.user?.let { userData ->
            User(
                id = userData.id,
                email = userData.email,
                firstName = userData.firstName,
                lastName = userData.lastName,
                middleName = userData.middleName,
                gender = userData.gender,
                age = userData.age,
            )
        }
    }
}
