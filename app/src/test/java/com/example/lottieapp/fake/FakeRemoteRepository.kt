package com.example.lottieapp.fake

import com.example.lottieapp.data.network.repository.RemoteRepository

class FakeRemoteRepository : RemoteRepository {
    var isBlogsUpdated = false
    var isRecentAnimationsUpdated = false
    var isFeaturedAnimationsUpdated = false
    var isPopularAnimationsUpdated = false
    var isAnimatorsUpdated = false

    override suspend fun updateRecentAnimations() {
        isRecentAnimationsUpdated = true
    }

    override suspend fun updateFeaturedAnimations() {
        isFeaturedAnimationsUpdated = true
    }

    override suspend fun updatePopularAnimations() {
        isPopularAnimationsUpdated = true
    }

    override suspend fun updateAnimators() {
        isAnimatorsUpdated = true
    }

    override suspend fun updateBlogs() {
        isBlogsUpdated = true
    }
}