package com.data.interceptor

import android.content.Context
import com.data.utils.NetUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/6
 */

class CacheInterceptor(private val mContext: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //获取网络状态
        val netWorkState = NetUtils.getNetworkState(mContext)
        //无网络，请求强制使用缓存
        if (netWorkState == NetUtils.NETWORK_NONE) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }

        val originalResponse = chain.proceed(request)
        val maxAge: Int
        when (netWorkState) {
            NetUtils.NETWORK_MOBILE//mobile network 情况下缓存一分钟
            -> {
                maxAge = 60
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build()
            }

            NetUtils.NETWORK_WIFI//wifi network 情况下不使用缓存
            -> {
                maxAge = 0
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build()
            }

            NetUtils.NETWORK_NONE//none network 情况下离线缓存4周
            -> {
                val maxStale = 60 * 60 * 24 * 4 * 7
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build()
            }
            else -> throw IllegalStateException("network state is Error!")
        }
    }
}
