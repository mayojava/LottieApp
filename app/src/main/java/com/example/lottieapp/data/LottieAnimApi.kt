package com.example.lottieapp.data

import com.example.lottieapp.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LottieAnimApi {
    @GET("featuredAnimations.json")
    fun featured(@Query("token") token: String): Response<ApiResponse<Featured>>

    @GET("popularAnimations.json")
    fun popular(@Query("token") token: String): Response<ApiResponse<Popular>>

    @GET("recentAnimations.json")
    fun recent(@Query("token") token: String): Response<ApiResponse<Recent>>

    @GET("featuredAnimators.json")
    fun animators(@Query("token") token: String): Response<ApiResponse<Animators>>

    @GET("blogs.json")
    fun blogs(@Query("token") token: String): Response<ApiResponse<Blogs>>
}
