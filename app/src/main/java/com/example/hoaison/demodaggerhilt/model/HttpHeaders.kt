package com.example.hoaison.demodaggerhilt.model

import android.os.Build

object HttpHeaders {
    var deviceID: String? = "92a0b694f70f6175"
    var pushToken: String? = null
    const val apiID: String = "API-ID-NEIGHBORFRIEND-MS"
    const val apiKey: String = "API-KEY-NEIGHBORFRIEND-MS"
    const val osType: String = "android"
    var osVersion: String = Build.VERSION.RELEASE
    var appVersion: String? = "1.3.3"
    var carrierName: String? = null
    var carrierCode: String? = null

    fun toJson() = HashMap(
        hashMapOf(
            "X-DEVICE-ID" to this.deviceID,
            "X-OS-TYPE" to this.osType,
            "X-OS-VERSION" to this.osVersion,
            "X-APP-VERSION" to this.appVersion,
            "X-API-ID" to this.apiID,
            "X-API-KEY" to this.apiKey,
            "X-PUSH-TOKEN" to this.pushToken,
            "X-CARRIER-NAME" to this.carrierName,
            "X-CARRIER-CODE" to this.carrierCode
        )
    )
}