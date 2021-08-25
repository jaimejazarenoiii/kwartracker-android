package com.kwartracker.android.signup.repository

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.kwartracker.android.SignupMutation
import com.kwartracker.android.login.model.User
import com.kwartracker.android.type.ProfileInput
import com.kwartracker.android.type.SignUpWithEmailInput
import com.kwartracker.android.utils.ApiHelper
import com.kwartracker.android.utils.network.KwartrackerApi
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class SignupRepositoryImpl @Inject constructor(private val api: KwartrackerApi) : SignupRepository {

    override suspend fun signUp(request: HashMap<String, Any>): SignupMutation.Data? {
        return ApiHelper.safeApiCall(Dispatchers.Main) {
            api.getApolloClient()
                .mutate(
                    SignupMutation(
                        SignUpWithEmailInput(
                            email = request["email"] as String,
                            password = request["password"] as String,
                            passwordConfirmation = request["passwordConfirmation"] as String,
                            profile = ProfileInput(
                                firstName = request["firstName"] as String,
                                lastName = request["lastName"] as String,
                                gender = request["gender"] as Int,
                                age = request["age"] as Int,
                                middleName = Input.optional(request["middleName"] as String)
                            )
                        )
                    )
                )
                .await()
        }
    }

    override suspend fun register(request: HashMap<String, Any>): User? {
        return signUp(request)?.signUpWithEmail?.user?.let { userData ->
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
