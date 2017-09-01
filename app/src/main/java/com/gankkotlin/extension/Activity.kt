package com.gankkotlin.extension

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/31
 */
fun Activity.fixHWInputMethodManagerLeak(context: Context?) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val lastSrvViewString = "mLastSrvView"

    try {
        val field = imm::class.java.getDeclaredField(lastSrvViewString)
        if (!field.isAccessible) {
            field.isAccessible = true
        }
        val lastSrvView = field.get(imm)
        if (lastSrvView != null && lastSrvView is View) {
            field.set(imm, null)
            Log.d("TAG", "fixHWInputMethodManagerLeak success, desContext = " + context)
        }
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}

fun Activity.fixInputMethodManagerLeak(context: Context?) {
    if (context == null) {
        return
    }
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val arr = arrayOf("mCurRootView", "mServedView", "mNextServedView")
    arr.indices
            .map { arr[it] }
            .forEach {
                try {
                    val f = imm.javaClass.getDeclaredField(it)
                    if (!f!!.isAccessible) {
                        f.isAccessible = true
                    }
                    val obj_get = f.get(imm)
                    if (obj_get != null && obj_get is View) {
                        if (obj_get.context === context) { // 被InputMethodManager持有引用的context是想要目标销毁的
                            f.set(imm, null) // 置空，破坏掉path to gc节点
                        }
                    }
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
}