package com.gankkotlin

import android.app.Application
import com.data.DataLayer
import com.squareup.leakcanary.LeakCanary

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/6
 */
class App : Application() {

    val INSTANCE by lazy { this }

    override fun onCreate() {
        super.onCreate()
        DataLayer.init(this, BuildConfig.API_HOST_URL)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}