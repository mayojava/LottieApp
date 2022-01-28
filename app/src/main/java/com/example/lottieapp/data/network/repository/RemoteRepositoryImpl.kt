package com.example.lottieapp.data.network.repository

import com.example.lottieapp.data.LottieAnimApi
import com.example.lottieapp.data.ObjectMapper
import com.example.lottieapp.data.local.EntityType
import com.example.lottieapp.data.local.LottieAnimationDao
import com.example.lottieapp.data.model.*
import com.example.lottieapp.data.network.connectivity.InternetConnectivityManager
import com.example.lottieapp.data.network.dispatchers.AppDispatchers
import com.example.lottieapp.di.TokensModule
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import kotlin.Result

class RemoteRepositoryImpl @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val lottieAnimApi: LottieAnimApi,
    private val lottieAnimationDao: LottieAnimationDao,
    private val internetConnectivityManager: InternetConnectivityManager,
    @Named("tokens") private val tokens: Map<String, String>
): RemoteRepository {
    override suspend fun updateRecentAnimations() {
        fetchAndSave(
            remoteCall = { lottieAnimApi.recent(tokens.getValue(TokensModule.KEY_RECENT)) },
            saveCall = {
                val result = it.getOrThrow().recent.results
                lottieAnimationDao.deleteAndInsertAnimations(
                    type = EntityType.RECENT,
                    ObjectMapper.toAnimationDbEntity(EntityType.RECENT, result)
                )
            }
        )
    }

    override suspend fun updateFeaturedAnimations() {
        fetchAndSave(
            remoteCall = { lottieAnimApi.featured(tokens.getValue(TokensModule.KEY_FEATURED)) },
            saveCall = {
                val result = it.getOrThrow().featured.results
                lottieAnimationDao.deleteAndInsertAnimations(
                    type = EntityType.FEATURED,
                    ObjectMapper.toAnimationDbEntity(EntityType.FEATURED, result)
                )
            }
        )
    }

    override suspend fun updatePopularAnimations() {
        fetchAndSave(
            remoteCall = {
                lottieAnimApi.popular(tokens.getValue(TokensModule.KEY_POPULAR))
            },
            saveCall = {
                val result = it.getOrThrow().popular.results
                lottieAnimationDao.deleteAndInsertAnimations(
                    type = EntityType.POPULAR,
                    ObjectMapper.toAnimationDbEntity(EntityType.POPULAR, result)
                )
            }
        )
    }

    override suspend fun updateAnimators() {
        fetchAndSave(
            remoteCall = {
                lottieAnimApi.animators(tokens.getValue(TokensModule.KEY_ANIMATORS))
            },
            saveCall = {
                val result = it.getOrThrow().featuredAnimators.results
                lottieAnimationDao.deleteAndInsertAnimators(ObjectMapper.toAnimatorDbEntity(result))
            }
        )
    }

    override suspend fun updateBlogs() {
        fetchAndSave(
            remoteCall = {
                lottieAnimApi.blogs(tokens.getValue(TokensModule.KEY_BLOGS))
            },
            saveCall = {
                val result = it.getOrThrow().blogs.results
                lottieAnimationDao.deleteAndInsertBlogs(ObjectMapper.toBlogDbEntity(result))
            }
        )
    }

    private suspend fun <T> fetchAndSave(
        remoteCall: suspend () -> Response<ApiResponse<T>>,
        saveCall: suspend (result: Result<T>) -> Unit
    ) {
        withContext(appDispatchers.ioDispatcher) {
            val response = execute(remoteCall)

            if (response.isSuccess) {
                saveCall(response)
            }
        }
    }

    private suspend fun <T> execute(request: suspend () -> Response<ApiResponse<T>>): Result<T> {
        val isConnected = internetConnectivityManager.hasInternetConnectivity()
        return if (isConnected) {
            val response = request.invoke()
            if (response.isSuccessful) {
                Result.success(response.body()!!.data)
            } else {
                Result.failure(Throwable(response.errorBody()?.string() ?: ""))
            }
        } else {
            Result.failure(Throwable("No internet connectivity"))
        }
    }
}