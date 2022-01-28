package com.example.lottieapp.data.repository

import com.example.lottieapp.data.local.BlogsEntity
import com.example.lottieapp.data.local.LottieAnimationDao
import com.example.lottieapp.data.network.dispatchers.AppDispatchers
import com.example.lottieapp.data.network.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class LottieAnimRepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val lottieAnimationDao: LottieAnimationDao,
    private val appDispatchers: AppDispatchers
) : LottieAnimRepository {
    override suspend fun getBlogs(): Flow<List<BlogsEntity>> {
        //remoteRepository.updateBlogs()
        return lottieAnimationDao
            .getAllBlogEntries()
            .onEmpty {  }
            .flowOn(appDispatchers.ioDispatcher)
    }
}