package com.arribas.amphibiansapp

import android.app.Application
import com.arribas.amphibiansapp.data.AppContainer
import com.arribas.amphibiansapp.data.DefaultAppContainer

class AmphibianApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer()
    }
}