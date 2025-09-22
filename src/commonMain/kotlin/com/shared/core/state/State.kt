package com.shared.core.state

import com.shared.core.error.AppError


sealed class State<T> {

    class Loading<T> : State<T>()
    data class Success<T>(val data: T? = null, val error: AppError? = null) : State<T>()
    data class Error<T>(val error: AppError) : State<T>()
}