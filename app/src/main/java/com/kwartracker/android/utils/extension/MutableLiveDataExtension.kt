package com.kwartracker.android.utils.extension

import androidx.lifecycle.MutableLiveData

fun <T : Any> MutableLiveData<T>.getOrDefault(default: T): T {
    return value ?: default
}

fun <T : Any> MutableLiveData<T>.get(): T {
    return value ?: throw IllegalArgumentException()
}
