package com.data

import android.app.Application
import com.data.interceptor.CacheInterceptor
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/6
 */
object DataLayer {

    lateinit var app: Application
    lateinit var apiHostUrl: String

    // OkHttp Config
    private const val RESPONSE_CACHE_FILE: String = "response_cache"
    const val RESPONSE_CACHE_SIZE = 10 * 1024 * 1024L
    const val HTTP_CONNECT_TIMEOUT = 10L
    const val HTTP_READ_TIMEOUT = 30L
    const val HTTP_WRITE_TIMEOUT = 10L

    val okHttpClient: OkHttpClient by lazy {
        val cacheDir = File(app.cacheDir, RESPONSE_CACHE_FILE)
        OkHttpClient.Builder()
                .cache(Cache(cacheDir, RESPONSE_CACHE_SIZE))
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                //可以添加拦截器
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(CacheInterceptor(app))
                .build()
    }

    val gson: Gson by lazy {
        GsonBuilder().create()
    }

    fun init(app: Application, apiHostUrl: String) {
        this.app = app
        this.apiHostUrl = apiHostUrl
        Stetho.initializeWithDefaults(app)
    }
}