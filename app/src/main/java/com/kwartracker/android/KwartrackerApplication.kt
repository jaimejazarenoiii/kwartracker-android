package com.kwartracker.android

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KwartrackerApplication : Application() {

    companion object {
        lateinit var instance: KwartrackerApplication private set
        val context: Context
            get() = instance.applicationContext
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
