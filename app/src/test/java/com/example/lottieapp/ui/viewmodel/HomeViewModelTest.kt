package com.example.lottieapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lottieapp.data.repository.LottieAnimRepository
import com.example.lottieapp.fake.FakeLottieAnimationDao
import com.example.lottieapp.fake.FakeRemoteRepository
import com.example.lottieapp.fake.TestDispatchers
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private val remoteRepository = FakeRemoteRepository()
    private val lottieAnimationDao = FakeLottieAnimationDao()
    private val dispatchers = TestDispatchers()

    private val factory: LottieAnimRepository.Factory = object : LottieAnimRepository.Factory {
        override fun create(scope: CoroutineScope): LottieAnimRepository {
            return LottieAnimRepository(
                remoteRepository = remoteRepository,
                lottieAnimationDao = lottieAnimationDao,
                appDispatchers = dispatchers,
                launchScope = scope
            )
        }
    }

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatchers.ioDispatcher)
        viewModel = HomeViewModel(factory = factory)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchesData_onInit() = runTest {
        val state = viewModel.viewState.value

        assertThat(state.data.blogs).isNotEmpty()
        assertThat(state.data.recentAnimations).isNotEmpty()
        assertThat(state.data.featuredAnimations).isNotEmpty()
        assertThat(state.data.popularAnimations).isNotEmpty()
        assertThat(state.data.animators).isNotEmpty()
        assertThat(state.isLoading).isFalse()

        assertThat(remoteRepository.isBlogsUpdated).isTrue()
        assertThat(remoteRepository.isRecentAnimationsUpdated).isTrue()
        assertThat(remoteRepository.isFeaturedAnimationsUpdated).isTrue()
        assertThat(remoteRepository.isPopularAnimationsUpdated).isTrue()
        assertThat(remoteRepository.isAnimatorsUpdated).isTrue()
    }
}