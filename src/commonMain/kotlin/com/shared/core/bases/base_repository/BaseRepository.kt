package com.shared.core.bases.base_repository


import com.shared.core.error.AppError
import com.shared.core.error.GeneralException
import com.shared.core.error.IoException
import com.shared.core.error.ResponseError
import com.shared.core.error.ResponseUnAuthorizedError
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.io.IOException
import com.shared.core.state.State


abstract class BaseRepository<RequestDto, ResponseDto> {

    var dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getOperationState(requestDto: RequestDto): State<ResponseDto> {
        return try {
            performApiCall(requestDto)
        } catch (e: IOException) {
            State.Error(getIoExceptionError(e))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            State.Error(getGeneralExceptionError(e))
        }
    }

    abstract suspend fun performApiCall(requestDto: RequestDto): State<ResponseDto>

    fun <T> getNotSuccessfulResponseState(response: HttpResponse): State<T> {
        return when (response.status.value) {
            401 -> State.Error(getUnauthorizedError(response))
            else -> State.Error(getNotSuccessfulResponseError(response))
        }
    }

    private fun getIoExceptionError(e: IOException) = AppError.E(
        exception = IoException(cause = e),
        message = "Failed to load data from Api with IOException:",
    )

    private fun getGeneralExceptionError(e: Exception) = AppError.E(
        exception = GeneralException(cause = e),
        message = "Failed to load data from Api with General exception",
    )

    private fun getNotSuccessfulResponseError(response: HttpResponse) = AppError.E(
        exception = ResponseError(),
        message = "Api request to url: ${response.request.url}: failed with code ${response.status.value}",
        extraData = response
    )

    private fun getUnauthorizedError(response: HttpResponse) = AppError.E(
        exception = ResponseUnAuthorizedError(),
        message = "Api request to url: ${response.request.url}: failed with code ${response.status.value}",
        extraData = response
    )

}