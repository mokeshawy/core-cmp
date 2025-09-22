package com.shared.core.error


open class AppErrorException(message: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)


class GeneralException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


class IoException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


class ResponseException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


class ResponseUnAuthorizedException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


class BadRequestException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


class ResponseError(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


class ResponseUnAuthorizedError(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


