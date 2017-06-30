package com.gankkotlin.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/29
 */
fun Date.dateToString(format: String): String? {
    val localSimpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
    return localSimpleDateFormat.format(this)
}

fun Date.stringToDate(dateStr: String, format: String): Date? {
    val localSimpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
    var localDate: Date? = null
    try {
        localDate = localSimpleDateFormat.parse(dateStr)
    } catch (localParseException: ParseException) {
        localParseException.printStackTrace()
    }
    return localDate
}
