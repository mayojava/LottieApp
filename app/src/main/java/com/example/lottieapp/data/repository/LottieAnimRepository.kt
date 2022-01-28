package com.example.lottieapp.data.repository

import com.example.lottieapp.data.local.*
import com.example.lottieapp.data.network.dispatchers.AppDispatchers
import com.example.lottieapp.data.network.repository.RemoteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LottieAnimRepository @AssistedInject constructor(
    private val remoteRepository: RemoteRepository,
    private val lottieAnimationDao: LottieAnimationDao,
    private val appDispatchers: AppDispatchers,
    @Assisted private val launchScope: CoroutineScope
)  {
    suspend fun getBlogs(): Flow<List<BlogsEntity>> {
        launchScope.launch { remoteRepository.updateBlogs() }
        return lottieAnimationDao
            .getAllBlogEntries()
            .flowOn(appDispatchers.ioDispatcher)
    }

    suspend fun getAnimators(): Flow<List<AnimatorsEntity>> {
        launchScope.launch { remoteRepository.updateAnimators() }
        return lottieAnimationDao
            .getAllAnimators()
            .flowOn(appDispatchers.ioDispatcher)
    }

    suspend fun getPopularAnimations(): Flow<List<AnimationEntity>> {
        launchScope.launch { remoteRepository.updatePopularAnimations() }
        return lottieAnimationDao
            .getAllAnimations(type = EntityType.POPULAR)
            .flowOn(appDispatchers.ioDispatcher)
    }

    suspend fun getRecentAnimations(): Flow<List<AnimationEntity>> {
        launchScope.launch { remoteRepository.updateRecentAnimations() }
        return lottieAnimationDao
            .getAllAnimations(type = EntityType.RECENT)
            .flowOn(appDispatchers.ioDispatcher)
    }

    suspend fun getFeaturedAnimations(): Flow<List<AnimationEntity>> {
        launchScope.launch { remoteRepository.updateFeaturedAnimations() }
        return lottieAnimationDao
            .getAllAnimations(type = EntityType.FEATURED)
            .flowOn(appDispatchers.ioDispatcher)
    }

    @AssistedFactory
    interface Factory {
        fun create(scope: CoroutineScope): LottieAnimRepository
    }
}