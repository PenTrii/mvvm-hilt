package com.example.hoaison.demodaggerhilt.di

import com.example.hoaison.demodaggerhilt.api.ApiService
import com.example.hoaison.demodaggerhilt.model.HttpHeaders
import com.example.hoaison.demodaggerhilt.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance() : ApiService {
        val logging: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val gson = GsonBuilder().serializeNulls().setPrettyPrinting().create()

        val client: OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(AuthenticationInterceptor())
                .connectTimeout(60 * 3, TimeUnit.SECONDS)
                .readTimeout(60 * 3, TimeUnit.SECONDS)
                .writeTimeout(60 * 3, TimeUnit.SECONDS)
                .build()

        val response = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()

        return response.create(ApiService::class.java)
    }
}

class AuthenticationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        for (key in HttpHeaders.toJson().keys) {
            HttpHeaders.toJson()[key]?.let { builder.header(key, it) }
        }
        val request = builder.build()
        return chain.proceed(request)
    }
}