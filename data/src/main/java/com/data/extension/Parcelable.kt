package com.data.extension

import android.os.Parcel
import android.os.Parcelable

/**
 * 描    述：Parcelable扩展
 * 作    者：xul@13322.com
 * 时    间：2017/6/6
 */
inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?)
        : Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }