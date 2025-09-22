package com.shared.core.error

data class AppException(val appError: AppError) : Exception()