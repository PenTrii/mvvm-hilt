package com.example.hoaison.demodaggerhilt.utils

import com.example.hoaison.demodaggerhilt.utils.Status.SUCCESS
import com.example.hoaison.demodaggerhilt.utils.Status.LOADING
import com.example.hoaison.demodaggerhilt.utils.Status.ERROR

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}

data class DataResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): DataResource<T> =
            DataResource(status = SUCCESS, data = data, message = null)

        fun <T> loading(): DataResource<T> =
            DataResource(status = LOADING, data = null, message = null)

        fun <T> error(data: T?, message: String): DataResource<T> =
            DataResource(status = ERROR, data = data, message = message)
    }
}