package com.hyparz.composeretrofitsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HyparzApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}