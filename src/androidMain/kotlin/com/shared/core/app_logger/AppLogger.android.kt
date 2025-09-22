package com.shared.core.app_logger

import android.util.Log

actual object AppLogger {

    actual fun e(
        priority: Int,
        tag: String,
        message: String,
        throwable: Throwable?,
    ) {
        if (throwable != null) {
            Log.e("priority: $priority tag: $tag", message, throwable)
        } else {
            Log.e("priority: $priority tag: $tag", message)
        }
    }

    actual fun w(priority: Int, tag: String, message: String) {
        Log.d("priority: $priority tag: $tag", message)
    }

    actual fun i(priority: Int, tag: String, message: String) {
        Log.i("priority: $priority tag: $tag", message)
    }
}