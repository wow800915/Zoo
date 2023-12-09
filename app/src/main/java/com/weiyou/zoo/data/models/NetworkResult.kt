package com.weiyou.zoo.data.models

internal sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    data class Error(val error: ErrorResponse?) : NetworkResult<Nothing>()
    data class Success<out T : Any>(val data: T?) : NetworkResult<T>()
}
