package com.gankkotlin.extension

import java.util.*

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/12
 */

fun Long.formatFileSize(): String {
    when {
        this == 0L -> return "0K"
        this >= 1024L * 1024L -> {
            val sizeInM = this / (1024 * 1024f)
            return String.format(Locale.getDefault(), "%.1f", sizeInM) + "M"
        }
        else -> {
            val sizeInK = this / 1024f
            return String.format(Locale.getDefault(), "%.1f", sizeInK) + "K"
        }
    }
}

fun Long.formatDuration(): String {
    try {
        if (this <= 0) {
            return String.format(Locale.US, "%02d:%02d", 0, 0)
        }
        val totalSeconds = this / 1000
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600

        return if (hours > 0)
            String.format(Locale.US, "%02d:%02d", hours * 60 + minutes, seconds)
        else
            String.format(Locale.US, "%02d:%02d", minutes, seconds)

    } catch (e: NumberFormatException) {
        return "0:00"
    }

}
