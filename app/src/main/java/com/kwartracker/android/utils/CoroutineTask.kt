package com.kwartracker.android.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kwartracker.android.utils.extension.launchWithLiveData
import com.kwartracker.android.utils.viewstate.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job

class CoroutineTask<T : Any>(
    private var scope: CoroutineScope = GlobalScope,
    private var task: suspend () -> T?
) {
    private var mutable = MutableLiveData<ViewState<T>>()
    val result: LiveData<ViewState<T>> = mutable
    private var job: Job? = null

    fun observe(owner: LifecycleOwner, handler: ViewState<T>.() -> Unit) {
        result.observe(
            owner,
            Observer {
                it?.let {
                    handler(it)
                }
            }
        )
    }

    fun run() {
        if (job != null) return
        launch()
    }

    fun cancelable() {
        if (job != null) {
            job?.cancel()
        }
        launch()
    }

    private fun launch() {
        job = scope.launchWithLiveData(mutable) {
            try {
                task()
            } finally {
                job = null
            }
        }
    }
}
