package com.example.hoaison.demodaggerhilt.repository

import com.example.hoaison.demodaggerhilt.data.OperationCallback
import com.example.hoaison.demodaggerhilt.data.UserRemoteDataSource
import com.example.hoaison.demodaggerhilt.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {
    suspend fun getUserList(
        callback: OperationCallback<User>,
        token: String,
        page: Int?,
        limit: Int?
    ) = userRemoteDataSource.getListUser(
        callback = callback,
        token = token,
        page = page,
        limit = limit
    )
}