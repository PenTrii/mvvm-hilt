package com.example.hoaison.demodaggerhilt.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code") val code: Int?,
    @SerializedName("message") val message: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("data") val data: T? = null
) {
    data class Data<T>(
        @SerializedName("result")
        val result: T? = null
    )
}