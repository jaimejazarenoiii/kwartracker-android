package com.kwartracker.android.utils.extension

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kwartracker.android.utils.CoroutineTask
import com.kwartracker.android.utils.viewstate.ViewState
import kotlinx.coroutines.*

fun <T : Any> CoroutineScope.launchWithLiveData(
    observer: MutableLiveData<ViewState<T>>,
    task: suspend () -> T?
): Job {
    return launch {
        withContext(Dispatchers.Main) {
            observer.value = ViewState.Loading()
        }
        runCatching {
            task()
        }.onSuccess { res ->
            withContext(Dispatchers.Main) {
                res?.let {
                    observer.value = ViewState.Success(res)
                }
            }
        }.onFailure { e ->
            withContext(Dispatchers.Main) {
                observer.value = ViewState.Error("error", e)
            }
        }
    }
}

fun <T : Any> LifecycleOwner.bindLoadingView(loading: View, livedata: CoroutineTask<T>) {
    bindLoadingView(loading, livedata.result)
}

fun <T : Any> LifecycleOwner.bindLoadingView(loading: View, livedata: TaskLiveData<T>) {
    livedata.observe(
        this,
        {
            it?.let {
            }
        }
    )
}

typealias TaskLiveData<T> = LiveData<ViewState<T>>
