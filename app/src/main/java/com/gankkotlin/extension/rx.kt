package com.gankkotlin.extension

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
fun <T> Observable<T>.onUI() = observeOn(AndroidSchedulers.mainThread())!!