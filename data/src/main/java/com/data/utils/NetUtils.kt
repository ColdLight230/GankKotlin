package com.data.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/6
 */

object NetUtils {
    /**
     * 网络是否打开

     * @param context
     * *
     * @return
     */
    fun isNetOpen(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null
    }

    /**
     * 打开网络设置界面
     * 3.0以下打开设置界面
     */
    fun openWirelessSettings(context: Context) {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            context.startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
        } else {
            context.startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS))
        }
    }

    /**
     * 判断是否网络连接
     * 需添加权限<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
     */
    fun isConnected(context: Context): Boolean {
        val cm = context
                .getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }

    /**
     * 判断wifi是否连接状态
     * 需添加权限<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
     */
    fun isWifiConnected(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 获取移动网络运营商名称
     * 如中国联通、中国移动、中国电信
     */
    fun getNetworkOperatorName(context: Context): String? {
        val tm = context
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.networkOperatorName
    }

    /**
     * 获取移动终端类型
     * PHONE_TYPE_NONE  : 0 手机制式未知
     * PHONE_TYPE_GSM   : 1 手机制式为GSM，移动和联通
     * PHONE_TYPE_CDMA  : 2 手机制式为CDMA，电信
     * PHONE_TYPE_SIP   : 3
     */
    fun getPhoneType(context: Context): Int {
        val tm = context
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.phoneType
    }

    /**
     * 获取连接的网络类型(2G,3G,4G)
     * 联通的3G为UMTS或HSDPA，移动和联通的2G为GPRS或EGDE，电信的2G为CDMA，电信的3G为EVDO
     */
    fun getNetworkTpye(context: Context): Int {
        val telephonyManager = context
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> return NETWORK_2G
            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> return NETWORK_3G
            TelephonyManager.NETWORK_TYPE_LTE -> return NETWORK_4G
            else -> return NETWORK_NONE
        }
    }

    /**
     * 获取当前手机的可用网络类型(WIFI,2G,3G,4G)
     * 需添加权限<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
     * 需要用到上面的方法
     */
    fun getNetworkTypeDetail(context: Context): Int {
        var netWorkType = NETWORK_NONE
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            val type = networkInfo.type
            if (type == ConnectivityManager.TYPE_WIFI) {
                netWorkType = NETWORK_WIFI
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = getNetworkTpye(context)
            }
        }
        return netWorkType
    }

    fun getNetworkState(context: Context): Int {
        val connManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Wifi
        val state: NetworkInfo? = connManager.activeNetworkInfo
        return when {
            state?.type == ConnectivityManager.TYPE_WIFI -> NETWORK_WIFI
            state?.type == ConnectivityManager.TYPE_MOBILE -> NETWORK_MOBILE
            else -> NETWORK_NONE
        }
    }


    val NETWORK_NONE = 0
    // wifi network
    val NETWORK_WIFI = 1
    // "2G" networks
    val NETWORK_2G = 2
    // "3G" networks
    val NETWORK_3G = 3
    // "4G" networks
    val NETWORK_4G = 4
    // moblie networks
    val NETWORK_MOBILE = 5
}
