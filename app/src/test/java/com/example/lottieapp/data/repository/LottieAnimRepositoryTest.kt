package com.example.lottieapp.data.repository

import com.example.lottieapp.fake.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LottieAnimRepositoryTest {
    private val remoteRepository = FakeRemoteRepository()
    private val lottieAnimationDao = FakeLottieAnimationDao()
    private val appDispatchers = TestDispatchers()
    private val unconfinedDispatcher = UnconfinedTestDispatcher()
    private val launchScope = TestScope(context = unconfinedDispatcher)

    private lateinit var lottieAnimRepository: LottieAnimRepository

    @Before
    fun setup() {
        lottieAnimRepository = LottieAnimRepository(
            remoteRepository, lottieAnimationDao, appDispatchers,
            launchScope
        )
    }

    @Test
    fun getPopularAnimations_makesRemoteCall() = runTest {
        assertThat(remoteRepository.isPopularAnimationsUpdated).isFalse()
        lottieAnimRepository.getPopularAnimations()
        assertThat(remoteRepository.isPopularAnimationsUpdated).isTrue()
    }

    @Test
    fun getPopularAnimations_GetsFromDB() = runTest {
        val res = lottieAnimRepository.getPopularAnimations().toList()

        assertThat(res).isNotEmpty()
        assertThat(res.size).isEqualTo(1)
    }

    @Test
    fun getAnimators_makesRemoteCall() = runTest {
        assertThat(remoteRepository.isAnimatorsUpdated).isFalse()
        lottieAnimRepository.getAnimators()
        assertThat(remoteRepository.isAnimatorsUpdated).isTrue()
    }

    @Test
    fun getAnimators_GetsFromDB() = runTest {
        val res = lottieAnimRepository.getAnimators().toList()

        assertThat(res).isNotEmpty()
        assertThat(res.first().first()).isEqualTo(animator)
    }

    @Test
    fun getBlogs_makeRemoteCall() = runTest {
        assertThat(remoteRepository.isBlogsUpdated).isFalse()
        lottieAnimRepository.getBlogs()
        assertThat(remoteRepository.isBlogsUpdated).isTrue()
    }

    @Test
    fun getBlogs_GetsFromDB() = runTest {
        val res = lottieAnimRepository.getBlogs().toList()

        assertThat(res).isNotEmpty()
        assertThat(res.first().first()).isEqualTo(blog)
    }

}