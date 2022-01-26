package com.example.lottieapp.data.repository

import com.example.lottieapp.data.local.BlogsEntity
import kotlinx.coroutines.flow.Flow

interface LottieAnimRepository {
    suspend fun getBlogs(): Flow<List<BlogsEntity>>
}