package com.data.bean

import android.support.annotation.StringDef

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/6
 */
object Category {
    const val 福利 = "福利"
    const val ANDROID = "Android"
    const val IOS = "iOS"
    const val 休息视频 = "休息视频"
    const val 拓展资源 = "拓展资源"
    const val 前端 = "前端"
    const val ALL = "all"

    @StringDef(福利, ANDROID, IOS, 休息视频, 拓展资源, 前端, ALL)
    @Retention(AnnotationRetention.SOURCE)
    annotation class CategoryConst
}