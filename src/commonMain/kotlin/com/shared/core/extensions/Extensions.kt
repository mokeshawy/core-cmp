package com.shared.core.extensions


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shared.core.error.AppError
import com.shared.core.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


fun ViewModel.viewModelScope(block: suspend () -> Unit) = this.viewModelScope.launch {
    block()
}


suspend inline fun <T> Flow<State<T>>.collectOnFlowState(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (AppError) -> Unit,
    crossinline onSuccess: suspend (Any) -> Unit,
) = collect {
    onLoading()
    when (it) {
        is State.Error -> onError(it.error)
        is State.Loading -> {}
        is State.Success -> onSuccess(it.data ?: return@collect)
    }
}