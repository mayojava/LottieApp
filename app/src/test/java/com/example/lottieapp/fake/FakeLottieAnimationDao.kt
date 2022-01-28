package com.example.lottieapp.fake

import com.example.lottieapp.data.local.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeLottieAnimationDao : LottieAnimationDao {
    private val animations = mutableListOf (
        recentAnimationEntity,
        featuredAnimation,
        popularAnimation
    )

    private val animators = mutableListOf(animator)
    private val blogs = mutableListOf(blog)

    override fun getAllAnimations(type: EntityType): Flow<List<AnimationEntity>> =
        flowOf(animations.filter { it.type == type })

    override fun getAllAnimators(): Flow<List<AnimatorsEntity>> =
        flow { emit(animators) }

    override fun getAllBlogEntries(): Flow<List<BlogsEntity>> {
        return flow {
            emit(blogs)
        }
    }

    override suspend fun insertAnimations(entities: List<AnimationEntity>) {
        animations.addAll(entities)
    }

    override suspend fun insertAnimators(animators: List<AnimatorsEntity>) {
        this.animators.addAll(animators)
    }

    override suspend fun insertBlogs(blogs: List<BlogsEntity>) {
        this.blogs.addAll(blogs)
    }

    override suspend fun deleteAnimations(type: EntityType) {
        animations.removeAll { it.type == type }
    }

    override suspend fun deleteAllBlogs() {
        blogs.clear()
    }

    override suspend fun deleteAllAnimators() {
        animators.clear()
    }
}
