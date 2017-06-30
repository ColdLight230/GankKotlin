package com.gankkotlin.extension

import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/10
 */
fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}
