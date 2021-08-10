package com.kwartracker.android.utils.viewstate

sealed class ViewState<T>(
    val value: T? = null,
    val throwable: Throwable? = null,
    val message: String? = null
) {
    open val isActive: Boolean = false

    class Success<T>(val data: T) : ViewState<T>(data) {
        override val isActive = false
    }

    class Error<T>(message: String? = null, val t: Throwable? = null) :
        ViewState<T>(throwable = t, message = message) {
        override val isActive = false
    }

    class Loading<T> : ViewState<T>() {
        override val isActive = true
    }

    private fun onComplete(
        onSuccess: ((T) -> Unit)? = null,
        onFailure: ((Throwable?) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ): ViewState<T> {
        return when (this) {
            is Loading -> {
                this
            }
            is Success -> {
                onSuccess?.invoke(data)
                onComplete?.invoke()
                this
            }
            is Error -> {
                onFailure?.invoke(t)
                onComplete?.invoke()
                this
            }
        }
    }

    fun onSuccess(handler: ((T) -> Unit)? = null): ViewState<T> = onComplete(onSuccess = handler)
    fun onFailure(handler: (Throwable?) -> Unit): ViewState<T> = onComplete(onFailure = handler)
}
