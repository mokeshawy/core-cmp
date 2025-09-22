package com.shared.core.app_logger

import platform.Foundation.NSLog

actual object AppLogger {

    actual fun e(
        priority: Int,
        tag: String,
        message: String,
        throwable: Throwable?,
    ) {

        if (throwable != null) {
            NSLog("PRIORITY: [$priority]. ERROR: [$tag] $message. Throwable: $throwable CAUSE ${throwable.cause}")
        } else {
            NSLog("PRIORITY: [$priority]. ERROR: [$tag] $message")
        }
    }

    actual fun w(priority: Int, tag: String, message: String) {
        NSLog("PRIORITY: [$priority]. WARNING: [$tag] $message")
    }

    actual fun i(priority: Int, tag: String, message: String) {
        NSLog("PRIORITY: [$priority]. INFO: [$tag] $message")
    }
}