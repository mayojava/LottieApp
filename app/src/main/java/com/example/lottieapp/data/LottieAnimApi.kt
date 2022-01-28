package com.example.lottieapp.data

import com.example.lottieapp.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LottieAnimApi {
    @GET("featuredAnimations.json")
    suspend fun featured(@Query("token") token: String): Response<ApiResponse<Featured>>

    @GET("popularAnimations.json")
    suspend fun popular(@Query("token") token: String): Response<ApiResponse<Popular>>

    @GET("recentAnimations.json")
    suspend fun recent(@Query("token") token: String): Response<ApiResponse<Recent>>

    @GET("featuredAnimators.json")
    suspend fun animators(@Query("token") token: String): Response<ApiResponse<Animators>>

    @GET("blogs.json")
    suspend fun blogs(@Query("token") token: String): Response<ApiResponse<Blogs>>
}
