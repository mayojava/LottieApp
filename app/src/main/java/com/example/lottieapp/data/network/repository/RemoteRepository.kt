package com.example.lottieapp.data.network.repository

interface RemoteRepository {
    suspend fun updateRecentAnimations()

    suspend fun updateFeaturedAnimations()

    suspend fun updatePopularAnimations()

    suspend fun updateAnimators()

    suspend fun updateBlogs()
}