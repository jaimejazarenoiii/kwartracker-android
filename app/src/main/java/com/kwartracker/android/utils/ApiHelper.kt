package com.kwartracker.android.utils

import com.apollographql.apollo.api.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NotFoundException(message: String) : Exception(message)

object ApiHelper {

    fun handleError(t: Throwable) {
        when (t) {
            is NotFoundException -> {
                ToastHelper.showText(t.localizedMessage)
            }
            else -> {
                ToastHelper.showText("An unexpected error has occurred")
            }
        }
    }

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> Response<T>
    ): T? {
        return withContext(dispatcher) {
            try {
                val response = apiCall.invoke()
                if (response.hasErrors()) throw NotFoundException(
                    response.errors?.get(0)?.message ?: ""
                )
                response.data
            } catch (t: Throwable) {
                handleError(t)
                null
            }
        }
    }
}
