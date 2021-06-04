package com.example.hoaison.demodaggerhilt.data

import com.example.hoaison.demodaggerhilt.api.ApiService
import com.example.hoaison.demodaggerhilt.model.User
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getListUser(
        callback: OperationCallback<User>,
        token: String,
        page: Int?,
        limit: Int?
    ) {
        apiService.getUserList(token).let { response ->
            if (response.isSuccessful) {
                if (response.body()?.code == 200)
                    callback.onSuccess(response.body()?.data?.result)
                else
                    callback.onError(response.message())
            }
            else {
                callback.onError(response.message())
            }
        }
    }
}