package com.example.lottieapp.data.network.repository

import com.example.lottieapp.data.local.EntityType
import com.example.lottieapp.di.TokensModule
import com.example.lottieapp.fake.FakeInternetConnectivityManager
import com.example.lottieapp.fake.FakeLottieAnimationApi
import com.example.lottieapp.fake.FakeLottieAnimationDao
import com.example.lottieapp.fake.TestDispatchers
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteRepositoryImplTest {
    private val dispatchers = TestDispatchers()
    private val lottieAnimationDao = FakeLottieAnimationDao()
    private val internetConnectivityManager = FakeInternetConnectivityManager()
    private val lottieAnimApi = FakeLottieAnimationApi(true)

    private lateinit var remoteRepository: RemoteRepository
    private val tokens = mapOf(
        TokensModule.KEY_FEATURED to "",
        TokensModule.KEY_RECENT to "",
        TokensModule.KEY_POPULAR to "",
        TokensModule.KEY_ANIMATORS to "",
        TokensModule.KEY_BLOGS to ""
    )

    @Before
    fun setup() {
        remoteRepository = RemoteRepositoryImpl(
            dispatchers,
            lottieAnimApi,
            lottieAnimationDao,
            internetConnectivityManager,
            tokens
        )
    }

    @Test
    fun check_noApiCallIsMade_ifNoInternetConnection() = runTest {
        internetConnectivityManager.setNetworkAvailable(false)

        remoteRepository.updateRecentAnimations()

        assertThat(lottieAnimApi.isRecentApiCalled).isFalse()
    }

    @Test
    fun updateRecentAnimations_fetchesFromRemote_saveInDb() = runTest {
        remoteRepository.updateRecentAnimations()

        assertThat(lottieAnimApi.isRecentApiCalled).isTrue()

        val animationsList = lottieAnimationDao.getAllAnimations(EntityType.RECENT)
        val animation = animationsList.first().first()

        assertThat(animation.id).isEqualTo(1)
        assertThat(animation.bgColor).isEqualTo("#fffff")
        assertThat(animation.name).isEqualTo("Rau Lom")
        assertThat(animation.createdAt).isEqualTo("2020-11-10")
        assertThat(animation.imageUrl).isEqualTo("https://lottie.png")
    }

    @Test
    fun updatePopularAnimations_fetchesFromRemote_saveInDb() = runTest {
        remoteRepository.updatePopularAnimations()

        assertThat(lottieAnimApi.isPopularApiCalled).isTrue()

        val animationsList = lottieAnimationDao.getAllAnimations(EntityType.POPULAR)
        val animation = animationsList.first().first()

        assertThat(animation.id).isEqualTo(1)
        assertThat(animation.bgColor).isEqualTo("#fffff")
        assertThat(animation.name).isEqualTo("Rau Lom")
        assertThat(animation.createdAt).isEqualTo("2020-11-10")
        assertThat(animation.imageUrl).isEqualTo("https://lottie.png")
    }

    @Test
    fun updateAnimators_fetchesFromRemote_saveInDb() = runTest {
        remoteRepository.updateAnimators()

        assertThat(lottieAnimApi.isAnimatorsApiCalled).isTrue()

        val dbAnimatorsList = lottieAnimationDao.getAllAnimators().toList()
        val animator = dbAnimatorsList.first().first()

        assertThat(animator.avatarUrl).isEqualTo("http://author.jpeg")
        assertThat(animator.name).isEqualTo("Anim Author")
    }

    @Test
    fun updateBlogs_fetchesFromRemote_savedInDb() = runTest {
        remoteRepository.updateBlogs()

        assertThat(lottieAnimApi.isBlogsApiCalled).isTrue()
        val ddBlogsList = lottieAnimationDao.getAllBlogEntries().toList()
        val blog = ddBlogsList.first().first()

        assertThat(blog.imageUrl).isEqualTo("https://avatar.png")
        assertThat(blog.title).isEqualTo("Learn Animations")
    }
}