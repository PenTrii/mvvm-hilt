package com.example.hoaison.demodaggerhilt.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("displayname")
    var displayName: String? = null,
    @SerializedName("avatar_url")
    val avatarURL: String? = null,
    @SerializedName("age")
    var ageID: String? = null
) {
    companion object {
        private var instance : User? = null

        fun setInstance(instance: User) {
            this.instance = instance
        }

        operator fun invoke() = synchronized(this) {
            if (instance == null)
                instance = User()
            instance!!
        }
    }
}