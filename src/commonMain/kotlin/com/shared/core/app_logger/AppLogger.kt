package com.shared.core.app_logger

expect object AppLogger {
    fun e(
        priority: Int,
        tag: String,
        message: String,
        throwable: Throwable?,
    )

    fun w(priority: Int, tag: String, message: String)
    fun i(priority: Int, tag: String, message: String)

}