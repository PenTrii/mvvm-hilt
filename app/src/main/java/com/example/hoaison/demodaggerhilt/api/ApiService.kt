package com.example.hoaison.demodaggerhilt.api

import com.example.hoaison.demodaggerhilt.model.BaseResponse
import com.example.hoaison.demodaggerhilt.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("user/find")
    suspend fun getUserList(
        @Query("token") token: String,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 30
    ): Response<BaseResponse<BaseResponse.Data<List<User>>>>
}